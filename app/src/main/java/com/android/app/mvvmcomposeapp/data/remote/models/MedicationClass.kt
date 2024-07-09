package com.android.app.mvvmcomposeapp.data.remote.models

data class MedicationClass(
    val associatedDrug: List<MedicationItem>,
    val associatedDrug2: List<MedicationItem>? // Optional field
)