package com.example.myrustore.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class DownloadAppUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    operator fun invoke(id : String) : Flow<Int>{
        return repository.downloadApp(id)
    }
}
