package com.example.myrustore.data.network

import com.example.myrustore.domain.AppCategory

data class AppItemDto(
    val id : String,
    val name : String?,
    val description : String?,
    val category : AppCategory?,
    val iconUrl : String?
)
