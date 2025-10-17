package com.example.myrustore.presentation

import androidx.lifecycle.ViewModel
import com.example.myrustore.data.AppsRepositoryImpl
import com.example.myrustore.domain.AppItem
import com.example.myrustore.domain.AppsRepository
import com.example.myrustore.domain.LoadAppsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _appState =
        MutableStateFlow<AppState>(AppState.Initial)
    val appState = _appState.asStateFlow()

    private val repository = AppsRepositoryImpl()
    private val loadAppsUseCase = LoadAppsUseCase(repository)

    init {
        loadApps()
    }

    fun loadApps(){
        val apps = loadAppsUseCase()
        _appState.value = AppState.Apps(apps)
    }

}