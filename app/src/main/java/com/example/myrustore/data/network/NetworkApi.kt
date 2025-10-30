package com.example.myrustore.data.network

import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkApi {

    @GET("/catalog")
    suspend fun getAppList() : List<AppItemDto>

    @GET("/catalog/{id}")
    suspend fun getAppDetails(@Path("id") id : String) : AppDetailsDto
}