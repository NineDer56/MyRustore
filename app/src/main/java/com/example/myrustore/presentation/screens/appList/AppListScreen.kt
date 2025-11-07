package com.example.myrustore.presentation.screens.appList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AppListScreen(
    contentPadding : PaddingValues,
    onAppClick : (id : String) -> Unit
) {

    val viewModel : AppListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    when(val currentState = state.value){

        is AppListState.Content -> {
            AppListContent(
                apps = currentState.appList,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(contentPadding),
                onAppClick = onAppClick
            )
        }
        is AppListState.Error -> {
            AppListError(
                modifier = Modifier.fillMaxSize(),
                onRefreshClick = {
                    viewModel.getAppList()
                }
            )
        }
        is AppListState.Loading -> {
            AppListLoading(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}