package com.example.myrustore.data.local

import androidx.room.TypeConverters
import com.example.myrustore.domain.AppCategory

class DatabaseConverter {

    @TypeConverters
    fun appCategoryToString(appCategory: AppCategory) : String {
        return appCategory.name
    }

    @TypeConverters
    fun stringToAppCategory(value : String) : AppCategory{
        val category = runCatching {
            AppCategory.valueOf(value)
        }.getOrElse {
            AppCategory.APP
        }
        return category
    }
}