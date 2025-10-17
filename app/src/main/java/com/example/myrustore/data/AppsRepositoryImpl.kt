package com.example.myrustore.data

import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository

class AppsRepositoryImpl : AppsRepository {

    private val networkApi = NetworkApi()
    private val mapper = Mapper()

    override fun loadApps(): List<AppItem> {
        return networkApi.loadApps().map {
            mapper.AppItemDtoToEntity(it)
        }
    }
}