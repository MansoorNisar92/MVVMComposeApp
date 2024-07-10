package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicationClass(
    @SerialName("className")
    val className: List<AssociatedDrugGroup>? = null,
    @SerialName("className2")
    val className2: List<AssociatedDrugGroup>? = null
)