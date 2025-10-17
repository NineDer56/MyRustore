package com.example.myrustore.domain

interface AppsRepository {

    fun loadApps() : List<AppItem>

}