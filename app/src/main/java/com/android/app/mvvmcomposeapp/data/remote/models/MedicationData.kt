package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicationData(
    val medicationsClasses: Map<String, MedicationClass>
)