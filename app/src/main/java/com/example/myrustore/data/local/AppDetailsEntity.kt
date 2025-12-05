package com.example.myrustore.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myrustore.domain.AppCategory

@Entity(
    tableName = "app_details",
    foreignKeys = [
        ForeignKey(
            entity = AppItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AppDetailsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val developer: String,
    val category: AppCategory,
    val ageRating: Int,
    val size: Float,
    @ColumnInfo("icon_url") val iconUrl: String,
    val description: String
)