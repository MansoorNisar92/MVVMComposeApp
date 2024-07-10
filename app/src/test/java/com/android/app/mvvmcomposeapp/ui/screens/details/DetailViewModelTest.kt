package com.android.app.mvvmcomposeapp.ui.screens.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockMedicationRepository: MedicationRepository

    private lateinit var detailViewModel: DetailViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        detailViewModel = DetailViewModel(mockMedicationRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetch medications`() = runTest {
        val mockMedications = listOf(
            MedicationItem(name = "asprin", dose = "", strength = "500 mg"),
            MedicationItem(name = "paracetamol", dose = "500 mg", strength = "500 mg")
        )

        Mockito.`when`(mockMedicationRepository.getMedications()).thenReturn(mockMedications)

        detailViewModel = DetailViewModel(mockMedicationRepository)

        advanceUntilIdle()

        assertEquals(mockMedications, detailViewModel.medications.first())
    }

    @Test
    fun `test get medication by id when medication exists`() = runTest {
        val mockMedications = listOf(
            MedicationItem(name = "asprin", dose = "", strength = "500 mg"),
            MedicationItem(name = "paracetamol", dose = "500 mg", strength = "500 mg")
        )

        Mockito.`when`(mockMedicationRepository.getMedications()).thenReturn(mockMedications)

        detailViewModel = DetailViewModel(mockMedicationRepository)

        advanceUntilIdle()

        val medication = detailViewModel.getMedicationById("asprin")
        assertEquals(mockMedications[0], medication)
    }

    @Test
    fun `test get medication by id when medication does not exist`() = runTest {
        val mockMedications = listOf(
            MedicationItem(name = "asprin", dose = "", strength = "500 mg"),
            MedicationItem(name = "paracetamol", dose = "500 mg", strength = "500 mg")
        )

        Mockito.`when`(mockMedicationRepository.getMedications()).thenReturn(mockMedications)

        detailViewModel = DetailViewModel(mockMedicationRepository)

        advanceUntilIdle()

        val medication = detailViewModel.getMedicationById("ibuprofen")
        assertNull(medication)
    }
}