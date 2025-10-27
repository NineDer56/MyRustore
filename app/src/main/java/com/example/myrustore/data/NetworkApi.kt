package com.example.myrustore.data

import javax.inject.Inject

class NetworkApi @Inject constructor() {

    fun loadApps() : List<AppItemDto>{
        val listOfItems: List<AppItemDto> = mutableListOf<AppItemDto>().apply {
            repeat(15) {
                this.add(it, AppItemDto(it))
            }
        }
        return listOfItems
    }

}