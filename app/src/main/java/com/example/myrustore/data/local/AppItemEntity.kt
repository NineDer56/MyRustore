package com.example.myrustore.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myrustore.domain.AppCategory

@Entity(tableName = "apps")
data class AppItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: AppCategory,
    @ColumnInfo(name = "icon_url") val iconUrl: String
)
