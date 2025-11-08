package com.example.myrustore.presentation.screens.appDetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AppDetailsScreen(
    contentPadding : PaddingValues,
    id : String
){
    // TODO нажатие на кнопку "перезагрузить" после появления интернета приводит к тому что скриншоты могут не загрузиться, нужно переделать dao
    val viewModel : AppDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val downloadState by viewModel.downloadState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAppDetails(id)
    }

    when(val currentState = state){
        is AppDetailsState.Loading -> {
            Log.d("Debug", currentState.toString())
            AppDetailsLoading(
                modifier = Modifier.fillMaxSize()
            )
        }

        is AppDetailsState.Content -> {
            Log.d("Debug", currentState.toString())
            AppDetailsContent(
                appDetails = currentState.appDetails,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(contentPadding),
                descriptionExpanded = currentState.descriptionExpanded,
                onReadMoreClick = {
                    viewModel.expandOrCollapseDescription()
                },
                onButtonDownloadClick = {
                    viewModel.downloadApp(id)
                },
                downloadState = downloadState
            )
        }

        is AppDetailsState.Error -> {
            AppDetailsError(
                onRefreshClick = {
                    viewModel.getAppDetails(id)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}