package com.example.myrustore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myrustore.data.local.AppDao
import com.example.myrustore.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context : Context
        ) : AppDataBase {
            return Room.databaseBuilder(
                context = context,
                klass = AppDataBase::class.java,
                name = DB_NAME
            ).build()
        }

        @Provides
        @Singleton
        fun provideDao(db : AppDataBase) : AppDao{
            return db.dao()
        }


        private const val DB_NAME = "myrustore.db"
    }
}