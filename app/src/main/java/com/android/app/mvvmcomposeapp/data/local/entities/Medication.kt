package com.android.app.mvvmcomposeapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)

fun Medication.toMedicationItem() = MedicationItem(
    name = name,
    dose = dose,
    strength = strength
)

fun List<Medication>.toMedicationItem(): List<MedicationItem> {
    val list = arrayListOf<MedicationItem>()
    this.forEach { list.add(it.toMedicationItem()) }
    return list
}

