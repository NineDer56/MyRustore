package com.example.myrustore.domain

class LoadAppsUseCase(
    private val repository: AppsRepository
) {
    operator fun invoke() : List<AppItem>{
        return repository.loadApps()
    }
}