package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicationItem(
    val name: String,
    val dose: String?,
    val strength: String
)