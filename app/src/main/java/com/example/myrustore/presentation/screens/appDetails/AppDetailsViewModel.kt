package com.example.myrustore.presentation.screens.appDetails

import androidx.lifecycle.ViewModel
import com.example.myrustore.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    init {
        getAppDetails()
    }


    fun getAppDetails(){

    }
}