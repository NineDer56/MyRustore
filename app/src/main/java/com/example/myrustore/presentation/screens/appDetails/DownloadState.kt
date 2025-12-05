package com.example.myrustore.presentation.screens.appDetails

sealed class DownloadState {
    data object Initial : DownloadState()
    data object Prepare : DownloadState()
    data class Loading(val percent: Int) : DownloadState()
    data object Finish : DownloadState()
}