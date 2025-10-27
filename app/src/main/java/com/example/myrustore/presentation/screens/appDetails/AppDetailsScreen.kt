package com.example.myrustore.presentation.screens.appDetails

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
fun AppDetailsScreen(
    contentPadding : PaddingValues
){

    val viewModel : AppDetailsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()


    when(val currentState = state.value){

        is AppDetailsState.Loading -> {
            AppDetailsLoading(
                modifier = Modifier.fillMaxSize()
            )
        }

        is AppDetailsState.Content -> {
            AppDetailsContent(
                appDetails = currentState.appDetails,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(contentPadding),
                onReadMoreClick = {

                }
            )
        }

        is AppDetailsState.Error -> {
            AppDetailsError(
                onRefreshClick = {

                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}