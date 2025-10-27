package com.example.myrustore.presentation.screens.appDetails

import com.example.myrustore.domain.AppDetails

sealed class AppDetailsState {

    data object Error : AppDetailsState()
    data object Loading : AppDetailsState()
    data class Content(
        val appDetails : AppDetails,
        val descriptionCollapsed : Boolean
    ) : AppDetailsState()

}