package com.android.app.mvvmcomposeapp.di

import android.content.Context
import androidx.room.Room
import com.android.app.mvvmcomposeapp.data.local.AppDatabase
import com.android.app.mvvmcomposeapp.data.local.MedicationDao
import com.android.app.mvvmcomposeapp.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideMedicationDao(appDatabase: AppDatabase): MedicationDao {
        return appDatabase.medicationDao()
    }
}