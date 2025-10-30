package com.example.myrustore.data.network

import android.util.Log
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import javax.inject.Inject

class NetworkMapper @Inject constructor() {
    fun appItemDtoToEntity(dto: AppItemDto): AppItem {
        Log.d("NetworkMapper", dto.toString())
        return AppItem(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            category = dto.category,
            iconUrl = dto.iconUrl
        )
    }

    fun appDetailsDtoToEntity(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            id = dto.id,
            name = dto.name,
            developer = dto.developer,
            category = dto.category,
            ageRating = dto.ageRating,
            size = dto.size,
            iconUrl = dto.iconUrl,
            screenshotUrlList = dto.screenshotUrlList,
            description = dto.description,
        )
    }
}