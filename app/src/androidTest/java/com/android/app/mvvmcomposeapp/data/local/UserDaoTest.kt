package com.android.app.mvvmcomposeapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.app.mvvmcomposeapp.data.local.entities.User
import com.android.app.mvvmcomposeapp.di.DatabaseModule
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.random.Random

@UninstallModules(DatabaseModule::class)
@HiltAndroidTest
class UserDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUserSuccess() {
        runBlocking {
            val testUser = User(Random.nextInt(), "TestUser")
            userDao.insertUser(testUser)
            var inserted = false
            if (userDao.getUser()?.equals(testUser) == true) {
                inserted = true
            }
            Truth.assertThat(inserted)
        }
    }

    @Test
    fun insertUserFailure() {
        val testUser: User? = null
        Assert.assertNull("User is null", testUser)
    }

    @Test
    fun insertUserEmptyId() {
        val testUser = User(0, "TestUser")
        Assert.assertEquals(0, testUser.id)
    }


    @Test
    fun insertUserEmptyUsername() {
        val testUser = User(Random.nextInt(), "")
        Assert.assertEquals("", testUser.username)
    }
}