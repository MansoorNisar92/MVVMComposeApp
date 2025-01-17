package com.android.app.mvvmcomposeapp.di

import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepositoryImpl
import com.android.app.mvvmcomposeapp.network.client.Services
import com.android.app.mvvmcomposeapp.network.client.ServicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMedicationRepository(
        medicationRepositoryImpl: MedicationRepositoryImpl
    ): MedicationRepository
}

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindServiceImpl(servicesImpl: ServicesImpl): Services
}*/
