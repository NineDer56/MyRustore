package com.example.myrustore.data.local

import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import javax.inject.Inject

class DatabaseMapper @Inject constructor() {

    fun appItemToDbModel(appItem: AppItem): AppItemEntity {
        return AppItemEntity(
            id = appItem.id,
            name = appItem.name,
            description = appItem.description,
            category = appItem.category,
            iconUrl = appItem.iconUrl
        )
    }

    fun appItemDbModelToDomain(appItemEntity: AppItemEntity): AppItem {
        return AppItem(
            id = appItemEntity.id,
            name = appItemEntity.name,
            description = appItemEntity.description,
            category = appItemEntity.category,
            iconUrl = appItemEntity.iconUrl
        )
    }

    fun appDetailsToDbModel(appDetails: AppDetails): AppDetailsEntity {
        return AppDetailsEntity(
            id = appDetails.id,
            name = appDetails.name,
            developer = appDetails.developer,
            category = appDetails.category,
            ageRating = appDetails.ageRating,
            size = appDetails.size,
            iconUrl = appDetails.iconUrl,
            description = appDetails.description,
        )
    }

    fun appDetailsDbModelToDomain(appDetailsEntity: AppDetailsEntity): AppDetails {
        return AppDetails(
            id = appDetailsEntity.id,
            name = appDetailsEntity.name,
            developer = appDetailsEntity.developer,
            category = appDetailsEntity.category,
            ageRating = appDetailsEntity.ageRating,
            size = appDetailsEntity.size,
            iconUrl = appDetailsEntity.iconUrl,
            description = appDetailsEntity.description,
            screenshotUrlList = emptyList()
        )
    }

    fun appScreenshotToDbModel(url : String, appId : String) : AppScreenshotEntity{
        return AppScreenshotEntity(
            id = 0,
            appId = appId,
            url = url
        )
    }

    fun appScreenshotDbModelToDomain(appScreenshotEntity: AppScreenshotEntity) : String{
        return appScreenshotEntity.url
    }
}