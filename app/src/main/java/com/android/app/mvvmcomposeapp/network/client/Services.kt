package com.android.app.mvvmcomposeapp.network.client

import com.android.app.mvvmcomposeapp.data.remote.models.MedicationResponse

interface Services {
    suspend fun getMedications(): MedicationResponse
}