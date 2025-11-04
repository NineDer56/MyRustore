package com.example.myrustore.presentation.screens.appDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrustore.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private var getAppDetailsJob : Job? = null

    fun expandOrCollapseDescription(){
        _state.update {  currentState ->
            if(currentState is AppDetailsState.Content){
                currentState.copy(
                    descriptionExpanded = !currentState.descriptionExpanded
                )
            } else {
                currentState
            }
        }
    }

    fun getAppDetails(id : String){
        getAppDetailsJob?.cancel()
        _state.value = AppDetailsState.Loading
        getAppDetailsJob = viewModelScope.launch {
            getAppDetailsUseCase(id)
                .catch {
                    _state.value = AppDetailsState.Error
                }
                .collect{
                    _state.value = AppDetailsState.Content(
                        appDetails = it,
                        descriptionExpanded = false
                    )
                }
        }
    }
}