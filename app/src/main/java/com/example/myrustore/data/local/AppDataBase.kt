package com.example.myrustore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        AppItemEntity::class,
        AppDetailsEntity::class,
        AppScreenshotEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dao(): AppDao
}