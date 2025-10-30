package com.example.myrustore.domain

import kotlinx.coroutines.flow.Flow

interface AppsRepository {

    suspend fun getAppList() : Flow<List<AppItem>>

    suspend fun getAppDetails(id : String) : Flow<AppDetails>

}