package com.example.myrustore.data

import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val networkApi : NetworkApi,
    private val mapper : Mapper,
) : AppsRepository {

    override fun loadApps(): List<AppItem> {
        return networkApi.loadApps().map {
            mapper.appItemDtoToEntity(it)
        }
    }
}