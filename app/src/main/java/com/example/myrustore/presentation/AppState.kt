package com.example.myrustore.presentation

import com.example.myrustore.domain.AppDetails

sealed class AppState {

    data class Apps(val apps: List<AppDetails>) : AppState()
    data object Initial : AppState()
    data object Loading : AppState()
    data object Error : AppState()
}