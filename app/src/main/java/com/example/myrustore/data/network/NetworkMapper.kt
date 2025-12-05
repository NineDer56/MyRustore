package com.example.myrustore.data.network

import com.example.myrustore.domain.AppCategory
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import javax.inject.Inject

class NetworkMapper @Inject constructor() {
    fun appItemDtoToEntity(dto: AppItemDto): AppItem {
        return AppItem(
            id = dto.id,
            name = if (dto.name.isNullOrBlank()) NO_NAME else dto.name,
            description = if (dto.description.isNullOrBlank()) NO_DESCRIPTION else dto.description,
            category = dto.category ?: AppCategory.APP,
            iconUrl = if(dto.iconUrl.isNullOrBlank()) NO_ICON_URL else dto.iconUrl
        )
    }

    fun appDetailsDtoToEntity(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            id = dto.id,
            name = if (dto.name.isNullOrBlank()) NO_NAME else dto.name,
            developer = if (dto.developer.isNullOrBlank()) NO_DEVELOPER else dto.developer,
            category = dto.category ?: AppCategory.APP,
            ageRating = dto.ageRating ?: NO_AGE_RATING,
            size = dto.size,
            iconUrl = if(dto.iconUrl.isNullOrBlank()) NO_ICON_URL else dto.iconUrl,
            screenshotUrlList = dto.screenshotUrlList?.toList() ?: emptyList(),
            description = if (dto.description.isNullOrBlank()) NO_DESCRIPTION else dto.description,
        )
    }

    companion object {
        const val NO_NAME = "Неизвестно"
        const val NO_DEVELOPER = "Отсутствует"
        const val NO_DESCRIPTION = "Отсутствует"
        const val NO_ICON_URL = ""
        const val NO_AGE_RATING = 0
    }
}