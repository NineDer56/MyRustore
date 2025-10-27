package com.example.myrustore.domain

import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    operator fun invoke(id : String) :AppDetails{
        TODO()
    }
}