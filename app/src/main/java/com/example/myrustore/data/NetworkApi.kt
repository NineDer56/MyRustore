package com.example.myrustore.data

import com.example.myrustore.domain.AppItem

class NetworkApi {

    fun loadApps() : List<AppItemDto>{
        val listOfItems: List<AppItemDto> = mutableListOf<AppItemDto>().apply {
            repeat(15) {
                this.add(it, AppItemDto(it))
            }
        }
        return listOfItems
    }

}