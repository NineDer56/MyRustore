package com.example.myrustore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(entity = AppItemEntity::class)
    suspend fun insertAppItem(appItemEntity: AppItemEntity)

    @Insert(entity = AppDetailsEntity::class)
    suspend fun insertAppDetails(appDetailsEntity: AppDetailsEntity)

    @Insert(entity = AppScreenshotEntity::class)
    suspend fun insertScreenshot(screenshotEntity: AppScreenshotEntity)

    @Query("SELECT * FROM apps")
    fun getAllAppItems() : Flow<List<AppItemEntity>>

    @Query("SELECT * FROM app_details WHERE id=:id")
    fun getAppDetailsById(id : String) : Flow<AppDetailsEntity?>

    @Query("SELECT * FROM app_screenshots WHERE app_id=:id")
    suspend fun getScreenshotsByAppId(id: String) : List<AppScreenshotEntity>
}