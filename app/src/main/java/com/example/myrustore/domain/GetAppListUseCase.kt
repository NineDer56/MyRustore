package com.example.myrustore.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppListUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke() : Flow<List<AppItem>> {
        return repository.getAppList()
    }
}