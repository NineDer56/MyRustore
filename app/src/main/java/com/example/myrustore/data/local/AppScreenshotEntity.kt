package com.example.myrustore.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_screenshots",
    indices = [Index("app_id")],
    foreignKeys = [
        ForeignKey(
            entity = AppDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["app_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AppScreenshotEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "app_id") val appId: String,
    val url: String
)