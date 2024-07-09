package com.android.app.mvvmcomposeapp.data.remote.repositories

import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationResponse
import com.android.app.mvvmcomposeapp.network.client.Services
import javax.inject.Inject

class MedicationRepositoryImpl @Inject constructor(
    private val services: Services
) : MedicationRepository {

    override suspend fun getMedications(): List<MedicationItem> {
        val response: MedicationResponse = services.getMedications()

        return response.problems.flatMap { (_, problem) ->
            problem.medications.flatMap { medicationData ->
                medicationData.medicationsClasses.flatMap { (_, medicationClass) ->
                    val drugList = mutableListOf<MedicationItem>()
                    drugList.addAll(medicationClass.associatedDrug)
                    medicationClass.associatedDrug2?.let { drugList.addAll(it) }
                    drugList
                }
            }
        }
    }
}