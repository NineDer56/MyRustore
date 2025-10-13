package com.example.myrustore.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.ui.graphics.vector.ImageVector

data class AppItem(
    val appId : Int,
    val appImage : ImageVector = Icons.Default.AccountBox,
    val appCategory : String = "Игры",
    val appName : String = "Гильдия Героев: Экшен ММО РПГ",
    val developerName : String = "VK Play",
    val ageRestrictions : String = "12+",
    val applicationSize : String = "224 MB",
    val appShortDescription : String = "Легендарный рейд героев",
    val appDescription : String = "Легендарный рейд героев в Фентези РПГ и еще очень очень очень очень очень очень очень большое описание",
    val screenshots : List<ImageVector> = listOf(
        Icons.Default.Build,
        Icons.Default.AccountBox,
        Icons.Default.Favorite,
        Icons.Default.MailOutline
    )
)
