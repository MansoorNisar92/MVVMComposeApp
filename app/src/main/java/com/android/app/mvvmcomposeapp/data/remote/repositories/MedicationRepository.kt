package com.android.app.mvvmcomposeapp.data.remote.repositories

import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem

interface MedicationRepository {
    suspend fun getMedications(): List<MedicationItem>
}