package com.android.app.mvvmcomposeapp.di

import android.content.Context
import androidx.room.Room
import com.android.app.mvvmcomposeapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) = Room.inMemoryDatabaseBuilder(
        app, AppDatabase::class.java
    ).allowMainThreadQueries().build()

}