package com.example.myrustore.data

import com.example.myrustore.data.local.AppDao
import com.example.myrustore.data.local.DatabaseMapper
import com.example.myrustore.data.network.NetworkApi
import com.example.myrustore.data.network.NetworkMapper
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val networkMapper: NetworkMapper,
    private val databaseMapper: DatabaseMapper,
    private val dao: AppDao
) : AppsRepository {

    /* TODO в идеале добавить политику обновления (пользователь никогда не увиидит обновленные данные)
    *  добавить отдельные методы в dao для единичных запросов (чтобы избежать повторного вызова dao.getAllAppItems
    * разобраться с транзакциями
     */

    override fun getAppList(): Flow<List<AppItem>> {
        return dao.getAllAppItems()
            .onStart {
                val cached = dao.getAllAppItems().first()
                if (cached.isEmpty()) {
                    val appItemDtoList = withContext(Dispatchers.IO) {
                        networkApi.getAppList()
                    }
                    val appItemList = appItemDtoList.map { networkMapper.appItemDtoToEntity(it) }
                    val appItemEntityList = appItemList.map { databaseMapper.appItemToDbModel(it) }
                    appItemEntityList.forEach {
                        dao.insertAppItem(it)
                    }
                }
            }
            .map { entities ->
                entities.map { appItemEntity ->
                    databaseMapper.appItemDbModelToDomain(appItemEntity)
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override fun getAppDetails(id: String): Flow<AppDetails> {

        return dao.getAppDetailsById(id)
            .onStart {
                val cached = dao.getAppDetailsById(id).first()
                if (cached == null) {
                    val appDetailsDto = withContext(Dispatchers.IO) {
                        networkApi.getAppDetails(id)
                    }
                    val appDetails = networkMapper.appDetailsDtoToEntity(appDetailsDto)
                    val appDetailsDbModel = databaseMapper.appDetailsToDbModel(appDetails)
                    val screenshotDbModel =
                        appDetails.screenshotUrlList.map {
                            databaseMapper.appScreenshotToDbModel(
                                url = it,
                                appId = appDetails.id
                            )
                        }

                    dao.insertAppDetails(appDetailsDbModel)
                    screenshotDbModel.forEach {
                        dao.insertScreenshot(it)
                    }
                }
            }
            .filterNotNull()
            .map { entity ->
                val appDetails = databaseMapper.appDetailsDbModelToDomain(entity)
                val screenshots = dao.getScreenshotsByAppId(entity.id)
                    .map {
                        databaseMapper.appScreenshotDbModelToDomain(it)
                    }
                val appDetailsWithScreenshots = appDetails.copy(
                    screenshotUrlList = screenshots
                )
                appDetailsWithScreenshots
            }
            .flowOn(Dispatchers.IO)
    }
}