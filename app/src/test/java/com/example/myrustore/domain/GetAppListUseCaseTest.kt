package com.example.myrustore.domain

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class GetAppListUseCaseTest {

    private val repository : AppsRepository = mockk()
    private val getAppListUseCase = GetAppListUseCase(repository)

    private fun appItem(
        id: String = "1",
        name: String = "My App",
        description: String = "Great app",
        category: AppCategory = AppCategory.APP,
        iconUrl: String = "https://cdn.example.com/icon.png"
    ) = AppItem(id, name, description, category, iconUrl)

    @Test
    fun `get app list EXPECT get from repository`() = runTest{
        //arrange
        val app1 = appItem(id="1")
        val app2 = appItem(id="2")
        val expected = flowOf(listOf(app1, app2))
        coEvery {  repository.getAppList() } returns expected


        //act & assert
        getAppListUseCase.invoke().test {
            assertThat(awaitItem()).containsExactly(app1, app2).inOrder()
            awaitComplete()
        }
        coVerify(exactly = 1) { repository.getAppList() }
    }

}