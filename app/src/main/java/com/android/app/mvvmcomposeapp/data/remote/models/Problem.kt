package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Problem(
    @SerialName("Diabetes")
    val diabetes: List<DiabetesDetail>? = null
)

@Serializable
data class DiabetesDetail(
    @SerialName("medications")
    val medications: List<MedicationGroup>? = null,
    @SerialName("labs")
    val labs: List<Lab>? = null
)

@Serializable
data class MedicationGroup(
    @SerialName("medicationsClasses")
    val medicationsClasses: List<MedicationClass>? = null
)


@Serializable
data class AssociatedDrugGroup(
    @SerialName("associatedDrug")
    val associatedDrugs: List<MedicationItem>? = null,
    @SerialName("associatedDrug#2")
    val associatedDrugs2: List<MedicationItem>? = null
)

@Serializable
data class Lab(
    @SerialName("missing_field")
    val missingField: String? = null
)