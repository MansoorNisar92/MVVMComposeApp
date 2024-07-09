package com.android.app.mvvmcomposeapp.di

import com.android.app.mvvmcomposeapp.network.client.Services
import com.android.app.mvvmcomposeapp.network.client.ServicesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKTorClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideKTorService(httpClient: HttpClient): Services {
        return ServicesImpl(httpClient)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindServiceImpl(servicesImpl: ServicesImpl): Services
}