package com.android.app.mvvmcomposeapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username: String
)