package com.example.myrustore.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
) : ViewModel() {

    private val _appState =
        MutableStateFlow<AppState>(AppState.Loading)
    val appState = _appState.asStateFlow()


}