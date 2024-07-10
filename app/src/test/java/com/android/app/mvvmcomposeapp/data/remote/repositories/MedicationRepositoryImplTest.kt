package com.android.app.mvvmcomposeapp.data.remote.repositories

import com.android.app.mvvmcomposeapp.data.remote.models.AssociatedDrugGroup
import com.android.app.mvvmcomposeapp.data.remote.models.DiabetesDetail
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationClass
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationGroup
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationResponse
import com.android.app.mvvmcomposeapp.data.remote.models.Problem
import com.android.app.mvvmcomposeapp.network.client.Services
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MedicationRepositoryImplTest {

    @Mock
    private lateinit var mockServices: Services

    private lateinit var medicationRepository: MedicationRepository

    @Before
    fun setUp() {
        medicationRepository = MedicationRepositoryImpl(mockServices)
    }

    @Test
    fun `test getMedications returns correct medications`() = runTest {
        // Given
        val mockMedicationItem1 =
            MedicationItem(name = "Drug1", dose = "Dose1", strength = "Strength1")
        val mockMedicationItem2 =
            MedicationItem(name = "Drug2", dose = "Dose2", strength = "Strength2")

        val mockMedicationResponse = MedicationResponse(
            problems = listOf(
                Problem(
                    diabetes = listOf(
                        DiabetesDetail(
                            medications = listOf(
                                MedicationGroup(
                                    medicationsClasses = listOf(
                                        MedicationClass(
                                            className = listOf(
                                                AssociatedDrugGroup(
                                                    associatedDrugs = listOf(mockMedicationItem1),
                                                    associatedDrugs2 = listOf(mockMedicationItem2)
                                                )
                                            ),
                                            className2 = null
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )

        Mockito.`when`(mockServices.getMedications()).thenReturn(mockMedicationResponse)

        // When
        val medications = medicationRepository.getMedications()

        // Then
        assertEquals(2, medications.size)
        assertEquals(mockMedicationItem1, medications[0])
        assertEquals(mockMedicationItem2, medications[1])
    }

    @Test
    fun `test getMedications returns empty list when response is empty`() = runTest {
        // Given
        val mockMedicationResponse = MedicationResponse(problems = emptyList())

        Mockito.`when`(mockServices.getMedications()).thenReturn(mockMedicationResponse)

        // When
        val medications = medicationRepository.getMedications()

        // Then
        assertEquals(0, medications.size)
    }

    @Test
    fun `test getMedications returns correct medications when nested structure is complex`() =
        runTest {
            // Given
            val mockMedicationItem1 =
                MedicationItem(name = "Drug1", dose = "Dose1", strength = "Strength1")
            val mockMedicationItem2 =
                MedicationItem(name = "Drug2", dose = "Dose2", strength = "Strength2")

            val mockMedicationResponse = MedicationResponse(
                problems = listOf(
                    Problem(
                        diabetes = listOf(
                            DiabetesDetail(
                                medications = listOf(
                                    MedicationGroup(
                                        medicationsClasses = listOf(
                                            MedicationClass(
                                                className = listOf(
                                                    AssociatedDrugGroup(
                                                        associatedDrugs = listOf(mockMedicationItem1),
                                                        associatedDrugs2 = listOf(
                                                            mockMedicationItem2
                                                        )
                                                    )
                                                ),
                                                className2 = listOf(
                                                    AssociatedDrugGroup(
                                                        associatedDrugs = listOf(mockMedicationItem2),
                                                        associatedDrugs2 = listOf(
                                                            mockMedicationItem1
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )

            Mockito.`when`(mockServices.getMedications()).thenReturn(mockMedicationResponse)

            // When
            val medications = medicationRepository.getMedications()

            // Then
            assertEquals(4, medications.size)
            assertEquals(mockMedicationItem1, medications[0])
            assertEquals(mockMedicationItem2, medications[1])
            assertEquals(mockMedicationItem2, medications[2])
            assertEquals(mockMedicationItem1, medications[3])
        }
}