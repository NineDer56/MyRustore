package com.example.myrustore.data

import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val networkApi : NetworkApi,
    private val mapper : Mapper,
) : AppsRepository {

    override fun loadApps(): List<AppDetails> {
        return networkApi.loadApps().map {
            mapper.appItemDtoToEntity(it)
        }
    }

    fun loadAppsItem(): List<AppItem> {
        val listOfItems: List<AppItem> = mutableListOf<AppItem>().apply {
            repeat(15) {
                this.add(it, AppItem(it))
            }
        }
        return listOfItems
    }
}