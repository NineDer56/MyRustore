package com.example.myrustore.presentation

import app.cash.turbine.test
import com.example.myrustore.MainDispatcherExtension
import com.example.myrustore.domain.AppCategory
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.GetAppListUseCase
import com.example.myrustore.presentation.screens.appList.AppListState
import com.example.myrustore.presentation.screens.appList.AppListViewModel
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.jupiter.api.extension.RegisterExtension

class AppListViewModelTest {

    private fun appItem(
        id: String = "1",
        name: String = "My App",
        description: String = "Great app",
        category: AppCategory = AppCategory.APP,
        iconUrl: String = "https://cdn.example.com/icon.png"
    ) = AppItem(id, name, description, category, iconUrl)

    private val getAppListUseCase : GetAppListUseCase = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after init EXPECT change state Loading to Content`() = runTest{
        //arrange
        val app1 = appItem(id = "1")
        val app2 = appItem(id = "2")
        val expected = listOf(app1, app2)
        coEvery { getAppListUseCase.invoke() } returns flowOf(expected)

        //act
        val vm = AppListViewModel(getAppListUseCase)

        //assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppListState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppListState.Content(expected))
            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getAppListUseCase.invoke() }
        confirmVerified(getAppListUseCase)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `usecase throws exception EXPECT state Error`() = runTest{
        // arrange
        coEvery { getAppListUseCase.invoke() } returns flow {throw RuntimeException()}

        // act
        val vm = AppListViewModel(getAppListUseCase)

        // assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppListState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppListState.Error)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { getAppListUseCase.invoke() }
        confirmVerified(getAppListUseCase)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `multiple emits in usecase EXPECT change state Content to updated Content`() = runTest{
        //arrange
        val appList1 = listOf(appItem("1"))
        val appList2 = listOf(appItem("2"))
        coEvery { getAppListUseCase.invoke() } returns flow{
            emit(appList1)
            emit(appList2)
        }

        //act
        val vm = AppListViewModel(getAppListUseCase)

        //assert
        vm.state.test {
            assertThat(awaitItem()).isEqualTo(AppListState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(AppListState.Content(appList1))
            assertThat(awaitItem()).isEqualTo(AppListState.Content(appList2))
            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getAppListUseCase.invoke() }
        confirmVerified(getAppListUseCase)
    }



    companion object{
        @RegisterExtension
        val mainExt = MainDispatcherExtension()
    }
}