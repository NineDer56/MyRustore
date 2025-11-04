package com.example.myrustore.presentation

import app.cash.turbine.test
import com.example.myrustore.MainDispatcherExtension
import com.example.myrustore.domain.AppCategory
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.GetAppDetailsUseCase
import com.example.myrustore.presentation.screens.appDetails.AppDetailsState
import com.example.myrustore.presentation.screens.appDetails.AppDetailsViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class AppDetailsViewModelTest {

    private val getAppDetailsUseCase : GetAppDetailsUseCase = mockk()

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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAppDetails EXPECT Content`() = runTest{
        // arrange
        val id1 = "1"
        val appDetails1 = appDetails(id1)
        coEvery { getAppDetailsUseCase(id1) } returns flowOf(appDetails1)

        // act
        val vm = AppDetailsViewModel(getAppDetailsUseCase)
        vm.getAppDetails(id1)

        // assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails1, false))
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { getAppDetailsUseCase(id1) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `multiple getAppDetails EXPECT Content updates`() = runTest{
        // arrange
        val id1 = "1"
        val id2 = "2"
        val appDetails1 = appDetails(id1)
        val appDetails2 = appDetails(id2)
        coEvery { getAppDetailsUseCase(id1) } returns flowOf(appDetails1)
        coEvery { getAppDetailsUseCase(id2) } returns flowOf(appDetails2)

        // act
        val vm = AppDetailsViewModel(getAppDetailsUseCase)

        // assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)

            vm.getAppDetails(id1)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails1, false))

            vm.getAppDetails(id2)
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails2, false))

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { getAppDetailsUseCase(id1) }
        coVerify(exactly = 1) { getAppDetailsUseCase(id2) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `usecase throws exception EXPECT Error`() = runTest{
        // arrange
        val id1 = "1"
        coEvery { getAppDetailsUseCase(id1) } returns flow{
            throw RuntimeException()
        }

        // act
        val vm = AppDetailsViewModel(getAppDetailsUseCase)

        // assert
        vm.state.test {
            vm.getAppDetails(id1)
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Error)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { getAppDetailsUseCase(id1) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `expandOrCollapseDescription EXPECT change descriptionExpanded in Content`() = runTest{
        //arrange
        val id1 = "1"
        val appDetails1 = appDetails(id1)
        coEvery { getAppDetailsUseCase.invoke(id1) } returns flowOf(appDetails1)

        //act
        val vm = AppDetailsViewModel(getAppDetailsUseCase)

        //assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)

            vm.getAppDetails(id1)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails1, false))

            vm.expandOrCollapseDescription()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails1, true))

            vm.expandOrCollapseDescription()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails1, false))
        }

        coVerify(exactly = 1) { getAppDetailsUseCase(id1) }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `second request finishes first EXPECT second Content`() = runTest{
        val id1 = "1"
        val id2 = "2"
        val appDetails1 = appDetails(id1)
        val appDetails2 = appDetails(id2)

        coEvery { getAppDetailsUseCase(id1) } returns flow{
            delay(50)
            emit(appDetails1)
        }
        coEvery { getAppDetailsUseCase(id2) } returns flow{
            emit(appDetails2)
        }

        val vm = AppDetailsViewModel(getAppDetailsUseCase)

        vm.state.test {
            vm.getAppDetails(id1)
            vm.getAppDetails(id2)

            assertThat(awaitItem()).isEqualTo(AppDetailsState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppDetailsState.Content(appDetails2, false))

            expectNoEvents()

            cancelAndIgnoreRemainingEvents()
        }

    }


    companion object {
        @RegisterExtension
        val mainExt = MainDispatcherExtension()
    }

}