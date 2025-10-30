package com.example.myrustore.di

import com.example.myrustore.data.AppsRepositoryImpl
import com.example.myrustore.data.network.NetworkApi
import com.example.myrustore.domain.AppsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAppRepositoryImplToInterface(impl: AppsRepositoryImpl): AppsRepository

    companion object {
        private const val BASE_URL = "http://185.103.109.134"

        @Provides
        @Singleton
        fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit
        }

        @Provides
        @Singleton
        fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
            return retrofit.create(NetworkApi::class.java)
        }


    }
}
