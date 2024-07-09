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
        val medications = mutableListOf<MedicationItem>()

        response.problems.forEach { problem ->
            problem.diabetes?.forEach { detail ->
                detail.medications?.forEach { medicationGroup ->
                    medicationGroup.medicationsClasses?.forEach { medicationClass ->
                        medicationClass.className?.forEach { associatedDrugGroup ->
                            associatedDrugGroup.associatedDrugs?.let { medications.addAll(it) }
                            associatedDrugGroup.associatedDrugs2?.let { medications.addAll(it) }
                        }
                        medicationClass.className2?.forEach { associatedDrugGroup ->
                            associatedDrugGroup.associatedDrugs?.let { medications.addAll(it) }
                            associatedDrugGroup.associatedDrugs2?.let { medications.addAll(it) }
                        }
                    }
                }
            }
        }
        return medications
    }
}