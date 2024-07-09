package com.android.app.mvvmcomposeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.app.mvvmcomposeapp.data.local.entities.Medication

@Dao
interface MedicationDao {
    @Insert
    suspend fun insertMedications(medications: List<Medication>)

    @Query("SELECT * FROM medications")
    suspend fun getMedications(): List<Medication>

    @Query("DELETE FROM medications")
    suspend fun deleteAllMedications()
}