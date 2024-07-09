package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicationResponse(
    val problems: Map<String, Problem>
)

