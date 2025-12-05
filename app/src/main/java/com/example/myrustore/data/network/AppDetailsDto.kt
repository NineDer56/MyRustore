package com.example.myrustore.data.network

import com.example.myrustore.domain.AppCategory

data class AppDetailsDto(
    val id : String,
    val name : String?,
    val developer : String?,
    val category : AppCategory?,
    val ageRating : Int?,
    val size : Float,
    val iconUrl : String?,
    val screenshotUrlList : List<String>?,
    val description : String?
)