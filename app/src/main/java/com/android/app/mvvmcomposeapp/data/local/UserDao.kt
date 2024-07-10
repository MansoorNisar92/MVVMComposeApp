package com.android.app.mvvmcomposeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.app.mvvmcomposeapp.data.local.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User  LIMIT 1")
    suspend fun getUser(): User?

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}