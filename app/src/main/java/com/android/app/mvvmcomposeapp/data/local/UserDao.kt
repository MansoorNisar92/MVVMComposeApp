package com.android.app.mvvmcomposeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.app.mvvmcomposeapp.data.local.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username LIMIT 1")
    suspend fun getUser(username: String): User?
}