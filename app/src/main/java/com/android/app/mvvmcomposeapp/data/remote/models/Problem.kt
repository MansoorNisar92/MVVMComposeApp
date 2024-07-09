package com.android.app.mvvmcomposeapp.data.remote.models

data class Problem(
    val medications: List<MedicationData>,
    val labs: List<Map<String, String>> // Adjust according to actual lab data
)