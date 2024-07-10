package com.android.app.mvvmcomposeapp.ui.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.app.mvvmcomposeapp.data.local.MedicationDao
import com.android.app.mvvmcomposeapp.data.local.UserDao
import com.android.app.mvvmcomposeapp.data.local.entities.Medication
import com.android.app.mvvmcomposeapp.data.local.entities.User
import com.android.app.mvvmcomposeapp.data.local.entities.toMedicationItem
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockUserDao: UserDao

    @Mock
    private lateinit var mockMedicationDao: MedicationDao

    @Mock
    private lateinit var mockMedicationRepository: MedicationRepository

    private lateinit var homeViewModel: HomeViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel(
            mockMedicationRepository,
            mockUserDao,
            mockMedicationDao
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test init fetches user and local medications`() = runTest {
        val mockUser = User(username = "testUser")
        val mockMedications = listOf(
            Medication(id = 1, name = "asprin", dose = "", strength = "500 mg")
        )

        Mockito.`when`(mockUserDao.getUser()).thenReturn(mockUser)
        Mockito.`when`(mockMedicationDao.getMedications()).thenReturn(mockMedications)

        homeViewModel = HomeViewModel(mockMedicationRepository, mockUserDao, mockMedicationDao)

        advanceUntilIdle()

        Assert.assertEquals(mockUser, homeViewModel.user.first())
        Assert.assertEquals(
            mockMedications.map { it.toMedicationItem() },
            homeViewModel.medications.first()
        )
        Assert.assertFalse(homeViewModel.isLoading.first())
    }

    @Test
    fun `test fetch and save medications when local data is empty`() = runTest {
        val mockMedications = listOf(
            MedicationItem(name = "asprin", dose = "", strength = "500 mg")
        )

        Mockito.`when`(mockMedicationDao.getMedications()).thenReturn(emptyList())
        Mockito.`when`(mockMedicationRepository.getMedications()).thenReturn(mockMedications)

        homeViewModel = HomeViewModel(mockMedicationRepository, mockUserDao, mockMedicationDao)

        advanceUntilIdle()
        Assert.assertEquals(mockMedications, homeViewModel.medications.first())
        Assert.assertFalse(homeViewModel.isLoading.first())
    }

    @Test
    fun `test fetch medications handles error`() = runTest {
        Mockito.`when`(mockMedicationDao.getMedications()).thenReturn(emptyList())
        Mockito.`when`(mockMedicationRepository.getMedications())
            .thenThrow(RuntimeException("Network error"))

        homeViewModel = HomeViewModel(mockMedicationRepository, mockUserDao, mockMedicationDao)

        advanceUntilIdle()

        Assert.assertTrue(homeViewModel.medications.first().isEmpty())
        Assert.assertFalse(homeViewModel.isLoading.first())
    }
}
