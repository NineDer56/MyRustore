package com.example.myrustore.domain

import javax.inject.Inject

class LoadAppsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    operator fun invoke() : List<AppItem>{
        return repository.loadApps()
    }
}