package com.android.app.mvvmcomposeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.app.mvvmcomposeapp.data.local.entities.Medication
import com.android.app.mvvmcomposeapp.data.local.entities.User

@Database(entities = [User::class, Medication::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun medicationDao(): MedicationDao
}