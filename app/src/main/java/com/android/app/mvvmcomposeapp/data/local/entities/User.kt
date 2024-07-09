package com.android.app.mvvmcomposeapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int = 0,
    val username: String
)