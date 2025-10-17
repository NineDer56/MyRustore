package com.example.myrustore.presentation

import com.example.myrustore.domain.AppItem

sealed class AppState {

    data class Apps(val apps: List<AppItem>) : AppState()
    data object Initial : AppState()
    data object Loading : AppState()
    data object Error : AppState()
}