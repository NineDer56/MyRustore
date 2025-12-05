package com.example.myrustore.domain

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class GetAppDetailsUseCaseTest {

    private fun appDetails(
        id: String = "1",
        name: String = "name",
        developer: String = "developer",
        category: AppCategory = AppCategory.APP,
        ageRating: Int = 3,
        size: Float = 3f,
        iconUrl: String = "url",
        screenshotUrlList: List<String> = emptyList(),
        description: String = "description"
    ): AppDetails {
        return AppDetails(
            id, name, developer, category, ageRating, size, iconUrl, screenshotUrlList, description
        )
    }

    private val repository: AppsRepository = mockk()
    private val getAppDetailsUseCase = GetAppDetailsUseCase(repository)

    @Test
    fun `get app details EXPECT details from repository`() = runTest {
        val id = "1"
        val appDetails = appDetails()
        val expected = flowOf(appDetails)

        coEvery { repository.getAppDetails(id) } returns expected

        getAppDetailsUseCase.invoke(id).test {
            assertThat(awaitItem()).isEqualTo(appDetails)
            awaitComplete()
        }

        coVerify(exactly = 1) { repository.getAppDetails(id) }
    }
}