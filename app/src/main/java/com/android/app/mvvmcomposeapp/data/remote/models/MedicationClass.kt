package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicationClass(
    val associatedDrug: List<MedicationItem>,
    val associatedDrug2: List<MedicationItem>?
)