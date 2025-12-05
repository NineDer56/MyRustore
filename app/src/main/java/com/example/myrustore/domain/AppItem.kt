package com.example.myrustore.domain

data class AppItem(
    val id : String,
    val name : String,
    val description : String,
    val category : AppCategory,
    val iconUrl : String
)