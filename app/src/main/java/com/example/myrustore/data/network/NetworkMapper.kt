package com.example.myrustore.data.network

import com.example.myrustore.domain.AppCategory
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import javax.inject.Inject

class NetworkMapper @Inject constructor() {
    fun appItemDtoToEntity(dto: AppItemDto): AppItem {
        return AppItem(
            id = dto.id,
            name = if (dto.name.isNullOrBlank()) "Неизвестно" else dto.name,
            description = if (dto.description.isNullOrBlank()) "Отсутствует" else dto.description,
            category = dto.category ?: AppCategory.APP,
            iconUrl = dto.iconUrl ?: ""
        )
    }

    fun appDetailsDtoToEntity(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            id = dto.id,
            name = if (dto.name.isNullOrBlank()) "Неизвестно" else dto.name,
            developer = if (dto.developer.isNullOrBlank()) "Отсутствует" else dto.developer,
            category = dto.category ?: AppCategory.APP,
            ageRating = dto.ageRating ?: 0,
            size = dto.size,
            iconUrl = dto.iconUrl ?: "",
            screenshotUrlList = dto.screenshotUrlList?.toList() ?: emptyList(),
            description = if (dto.description.isNullOrBlank()) "Отсутствует" else dto.description,
        )
    }
}