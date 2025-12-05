package com.example.myrustore.presentation.screens.appDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrustore.domain.DownloadAppUseCase
import com.example.myrustore.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val downloadAppUseCase: DownloadAppUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _downloadState = MutableStateFlow<DownloadState>(DownloadState.Initial)
    val downloadState = _downloadState.asStateFlow()

    private var getAppDetailsJob : Job? = null
    private var downloadJob : Job? = null

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
        getAppDetailsJob = viewModelScope.launch {
            _state.value = AppDetailsState.Loading

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

    fun downloadApp(id : String){
        if(downloadJob != null) return
        downloadJob = viewModelScope.launch {
            downloadAppUseCase(id)
                .onStart {
                    _downloadState.value = DownloadState.Prepare
                    delay(300)
                }
                .onCompletion {
                    _downloadState.value = DownloadState.Finish
                }
                .collect{
                    _downloadState.value = DownloadState.Loading(it)
                }
        }
    }
}