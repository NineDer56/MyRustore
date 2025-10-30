package com.example.myrustore.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(id : String) : Flow<AppDetails> {
        return repository.getAppDetails(id)
    }
}