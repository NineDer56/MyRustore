package com.example.myrustore.data.network

import com.example.myrustore.data.network.NetworkMapper.Companion.NO_AGE_RATING
import com.example.myrustore.data.network.NetworkMapper.Companion.NO_DESCRIPTION
import com.example.myrustore.data.network.NetworkMapper.Companion.NO_DEVELOPER
import com.example.myrustore.data.network.NetworkMapper.Companion.NO_ICON_URL
import com.example.myrustore.data.network.NetworkMapper.Companion.NO_NAME
import com.example.myrustore.domain.AppCategory
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class NetworkMapperTest {

    private val TEST_URL = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI"
    private val mapper = NetworkMapper()

    @Test
    fun `appItemDto has null fields EXPECT defaults`(){
        val appItemDto = AppItemDto(
            id = "1",
            name = null,
            description = null,
            category = null,
            iconUrl = ""
        )

        val appItem = mapper.appItemDtoToEntity(appItemDto)

        assertThat(appItem.id).isEqualTo("1")
        assertThat(appItem.name).isEqualTo(NO_NAME)
        assertThat(appItem.description).isEqualTo(NO_DESCRIPTION)
        assertThat(appItem.category).isEqualTo(AppCategory.APP)
        assertThat(appItem.iconUrl).isEqualTo(NO_ICON_URL)
    }


    @Test
    fun `appDetailsDto has null fields EXPECT defaults`(){
        val appDetailsDto = AppDetailsDto(
            id = "1",
            name = null,
            developer = null,
            category = null,
            ageRating = null,
            size = 3.4f,
            iconUrl = null,
            screenshotUrlList = null,
            description = null
        )

        val appDetails = mapper.appDetailsDtoToEntity(appDetailsDto)

        assertThat(appDetails.id).isEqualTo("1")
        assertThat(appDetails.name).isEqualTo(NO_NAME)
        assertThat(appDetails.developer).isEqualTo(NO_DEVELOPER)
        assertThat(appDetails.category).isEqualTo(AppCategory.APP)
        assertThat(appDetails.ageRating).isEqualTo(NO_AGE_RATING)
        assertThat(appDetails.iconUrl).isEqualTo(NO_ICON_URL)
        assertThat(appDetails.screenshotUrlList).isEmpty()
        assertThat(appDetails.description).isEqualTo(NO_DESCRIPTION)
    }


    @Test
    fun `appItemDto has blank fields EXPECT defaults`(){
        val appItemDto = AppItemDto(
            id = "1",
            name = "",
            description = "",
            category = null,
            iconUrl = ""
        )

        val appItem = mapper.appItemDtoToEntity(appItemDto)

        assertThat(appItem.id).isEqualTo("1")
        assertThat(appItem.name).isEqualTo(NO_NAME)
        assertThat(appItem.description).isEqualTo(NO_DESCRIPTION)
        assertThat(appItem.category).isEqualTo(AppCategory.APP)
        assertThat(appItem.iconUrl).isEqualTo(NO_ICON_URL)
    }


    @Test
    fun `appDetailsDto has blank fields EXPECT defaults`(){
        val appDetailsDto = AppDetailsDto(
            id = "1",
            name = "",
            developer = "",
            category = null,
            ageRating = null,
            size = 3.4f,
            iconUrl = null,
            screenshotUrlList = null,
            description = ""
        )

        val appDetails = mapper.appDetailsDtoToEntity(appDetailsDto)

        assertThat(appDetails.id).isEqualTo("1")
        assertThat(appDetails.name).isEqualTo(NO_NAME)
        assertThat(appDetails.developer).isEqualTo(NO_DEVELOPER)
        assertThat(appDetails.category).isEqualTo(AppCategory.APP)
        assertThat(appDetails.ageRating).isEqualTo(NO_AGE_RATING)
        assertThat(appDetails.iconUrl).isEqualTo(NO_ICON_URL)
        assertThat(appDetails.screenshotUrlList).isEmpty()
        assertThat(appDetails.description).isEqualTo(NO_DESCRIPTION)
    }

    @Test
    fun `map appItemDto EXPECT real fields`(){
        val appItemDto = AppItemDto(
            id = "1",
            name = "name",
            description = "description",
            category = AppCategory.MAPS,
            iconUrl = TEST_URL
        )

        val appItem = mapper.appItemDtoToEntity(appItemDto)

        assertThat(appItem.id).isEqualTo("1")
        assertThat(appItem.name).isEqualTo("name")
        assertThat(appItem.description).isEqualTo("description")
        assertThat(appItem.category).isEqualTo(AppCategory.MAPS)
        assertThat(appItem.iconUrl).isEqualTo(TEST_URL)
    }


    @Test
    fun `map appDetailsDto EXPECT real fields`(){
        val appDetailsDto = AppDetailsDto(
            id = "1",
            name = "name",
            developer = "developer",
            category = AppCategory.MAPS,
            ageRating = 18,
            size = 3.4f,
            iconUrl = TEST_URL,
            screenshotUrlList = listOf(TEST_URL),
            description = "description"
        )

        val appDetails = mapper.appDetailsDtoToEntity(appDetailsDto)

        assertThat(appDetails.id).isEqualTo("1")
        assertThat(appDetails.name).isEqualTo("name")
        assertThat(appDetails.developer).isEqualTo("developer")
        assertThat(appDetails.category).isEqualTo(AppCategory.MAPS)
        assertThat(appDetails.ageRating).isEqualTo(18)
        assertThat(appDetails.size).isEqualTo(3.4f)
        assertThat(appDetails.iconUrl).isEqualTo(TEST_URL)
        assertThat(appDetails.screenshotUrlList).containsExactly(TEST_URL)
        assertThat(appDetails.description).isEqualTo("description")
    }
}