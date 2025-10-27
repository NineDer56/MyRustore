package com.example.myrustore.presentation.screens.appList

import androidx.lifecycle.ViewModel
import com.example.myrustore.data.AppsRepositoryImpl
import com.example.myrustore.data.Mapper
import com.example.myrustore.data.NetworkApi
import com.example.myrustore.domain.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppListUseCase: GetAppListUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    init {
        getAppList()
    }

    fun getAppList(){
        val apps = AppsRepositoryImpl(NetworkApi(), Mapper()).loadAppsItem()
        _state.value = AppListState.Content(apps)
        //TODO
    }
}