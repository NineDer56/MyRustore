package com.example.myrustore.domain

import kotlinx.coroutines.flow.Flow

interface AppsRepository {

    fun getAppList() : Flow<List<AppItem>>

    fun getAppDetails(id : String) : Flow<AppDetails>

}