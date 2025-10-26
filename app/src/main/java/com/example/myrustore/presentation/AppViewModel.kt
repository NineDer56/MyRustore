package com.example.myrustore.presentation

import androidx.lifecycle.ViewModel
import com.example.myrustore.domain.LoadAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val loadAppsUseCase : LoadAppsUseCase
) : ViewModel() {

    private val _appState =
        MutableStateFlow<AppState>(AppState.Initial)
    val appState = _appState.asStateFlow()


    init {
        loadApps()
    }

    fun loadApps(){
        val apps = loadAppsUseCase()
        _appState.value = AppState.Apps(apps)
    }

}