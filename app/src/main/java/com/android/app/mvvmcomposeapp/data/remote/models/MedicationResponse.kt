package com.android.app.mvvmcomposeapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicationResponse(
    @SerialName("problems")
    val problems: List<Problem> = arrayListOf()
)

