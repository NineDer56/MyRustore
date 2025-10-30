package com.example.myrustore.data

import com.example.myrustore.data.network.NetworkApi
import com.example.myrustore.data.network.NetworkMapper
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val networkApi : NetworkApi,
    private val networkMapper : NetworkMapper,
) : AppsRepository {

    override suspend fun getAppList(): Flow<List<AppItem>> {
        val appItemDtoList = networkApi.getAppList()

        return flow {
            val appItemList = appItemDtoList
                .map { networkMapper.appItemDtoToEntity(it) }
            emit(appItemList)
        }
    }

    override suspend fun getAppDetails(id: String): Flow<AppDetails> {
        val appDetailsDto = networkApi.getAppDetails(id)

        return flow {
            val appDetails = networkMapper.appDetailsDtoToEntity(appDetailsDto)
            emit(appDetails)
        }
    }
}