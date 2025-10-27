package com.example.myrustore.presentation.screens.appList

import com.example.myrustore.domain.AppItem

sealed class AppListState {

    data object Loading : AppListState()
    data object Error : AppListState()
    data class Content(
        val appList : List<AppItem>
    ) : AppListState()
}