package com.android.app.mvvmcomposeapp.ui.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.app.mvvmcomposeapp.data.local.AppDatabase
import com.android.app.mvvmcomposeapp.data.local.MedicationDao
import com.android.app.mvvmcomposeapp.data.local.UserDao
import com.android.app.mvvmcomposeapp.data.local.entities.User
import com.android.app.mvvmcomposeapp.ui.screens.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockAppDatabase: AppDatabase

    @Mock
    private lateinit var mockUserDao: UserDao

    @Mock
    private lateinit var mockMedicationDao: MedicationDao

    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(mockAppDatabase.userDao()).thenReturn(mockUserDao)
        Mockito.`when`(mockAppDatabase.medicationDao()).thenReturn(mockMedicationDao)
        viewModel = LoginViewModel(mockAppDatabase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test init clears medications`() = runTest {
        Mockito.verify(mockMedicationDao).deleteAllMedications()
    }

    @Test
    fun `test setUsername inserts user and sets userInserted to true`() = runTest {
        val username = "testUser"
        viewModel.setUsername(username)

        advanceUntilIdle()

        Mockito.verify(mockUserDao).deleteUser()
        Mockito.verify(mockUserDao).insertUser(User(username = username))
        Assert.assertTrue(viewModel.userInserted.value)
    }
}
