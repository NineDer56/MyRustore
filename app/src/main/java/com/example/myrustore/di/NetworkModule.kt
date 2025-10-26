package com.example.myrustore.di

import com.example.myrustore.data.AppsRepositoryImpl
import com.example.myrustore.data.Mapper
import com.example.myrustore.domain.AppsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindAppRepositoryImplToInterface(impl : AppsRepositoryImpl) : AppsRepository

    companion object {
        @Provides
        @Singleton
        fun provideMapper() = Mapper()
    }
}