package com.example.myrustore.data

import android.util.Log
import com.example.myrustore.data.local.AppDao
import com.example.myrustore.data.local.DatabaseMapper
import com.example.myrustore.data.network.NetworkApi
import com.example.myrustore.data.network.NetworkMapper
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val networkMapper: NetworkMapper,
    private val databaseMapper: DatabaseMapper,
    private val dao: AppDao
) : AppsRepository {
    // TODO довести реализацию до идеала
    //TODO тесты
    override suspend fun getAppList(): Flow<List<AppItem>> {
        return dao.getAllAppItems().map { entityList ->
            if (entityList.isNotEmpty()) {
                entityList.map {
                    databaseMapper.appItemDbModelToDomain(it)
                }
            } else {
                val appItemDtoList = networkApi.getAppList()
                val appItemList = appItemDtoList.map { networkMapper.appItemDtoToEntity(it) }
                val appItemDbModelList = appItemList.map { databaseMapper.appItemToDbModel(it) }
                withContext(Dispatchers.IO) {
                    appItemDbModelList.forEach {
                        dao.insertAppItem(it)
                    }
                }
                appItemList
            }
        }
    }

    override suspend fun getAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetailsById(id).map { entity ->
            Log.d("dbtest", "entity: ${entity.toString()}")
            if (entity != null) {
                val appDetails = databaseMapper.appDetailsDbModelToDomain(entity)
                Log.d("dbtest", appDetails.toString())
                val screenshots = withContext(Dispatchers.IO) {
                    dao.getScreenshotsByAppId(appDetails.id)
                }.map {
                    databaseMapper.appScreenshotDbModelToDomain(it)
                }
                val appDetailsWithScreenshots = appDetails.copy(screenshotUrlList = screenshots)
                Log.d("dbtest", appDetailsWithScreenshots.toString())
                appDetailsWithScreenshots
            } else {
                Log.d("dbtest", "getAppDetails else statement")
                val appDetailsDto = networkApi.getAppDetails(id)
                val appDetails = networkMapper.appDetailsDtoToEntity(appDetailsDto)
                val appDetailsDbModel = databaseMapper.appDetailsToDbModel(appDetails)
                val screenshotDbModel =
                    appDetails.screenshotUrlList.map {
                        databaseMapper.appScreenshotToDbModel(
                            url = it,
                            appId = appDetails.id
                        )
                    }

                withContext(Dispatchers.IO) {
                    dao.insertAppDetails(appDetailsDbModel)
                    screenshotDbModel.forEach {
                        dao.insertScreenshot(it)
                    }
                }

                appDetails
            }
        }
    }
}