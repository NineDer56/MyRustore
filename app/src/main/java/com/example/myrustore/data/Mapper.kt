package com.example.myrustore.data

import com.example.myrustore.domain.AppItem
import javax.inject.Inject

class Mapper() {
    fun appItemDtoToEntity(dto: AppItemDto): AppItem {
        return AppItem(
            appId = dto.appId,
            appImage = dto.appImage,
            appCategory = dto.appCategory,
            appName = dto.appName,
            developerName = dto.developerName,
            ageRestrictions = dto.ageRestrictions,
            applicationSize = dto.applicationSize,
            appShortDescription = dto.appShortDescription,
            appDescription = dto.appDescription,
            screenshots = dto.screenshots
        )
    }
}