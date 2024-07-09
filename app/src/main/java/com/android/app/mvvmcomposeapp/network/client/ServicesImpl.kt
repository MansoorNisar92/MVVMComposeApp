package com.android.app.mvvmcomposeapp.network.client

import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationResponse
import com.android.app.mvvmcomposeapp.network.Routes
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ServicesImpl(private val httpClient: HttpClient) : Services {
    override suspend fun getMedications(): MedicationResponse {
        return httpClient.get(Routes.MEDICATIONS)
    }
}