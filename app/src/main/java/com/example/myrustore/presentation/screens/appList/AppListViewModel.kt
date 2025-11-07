package com.example.myrustore.presentation.screens.appList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrustore.domain.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
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

    private fun getAppList(){
        viewModelScope.launch {
            _state.value = AppListState.Loading

            getAppListUseCase()
                .catch {
                    _state.value = AppListState.Error
                }
                .collect{
                    _state.value = AppListState.Content(it)
                }
        }

    }
}