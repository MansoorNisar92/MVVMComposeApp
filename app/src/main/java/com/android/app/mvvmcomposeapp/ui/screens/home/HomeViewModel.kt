package com.android.app.mvvmcomposeapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.mvvmcomposeapp.data.local.MedicationDao
import com.android.app.mvvmcomposeapp.data.local.UserDao
import com.android.app.mvvmcomposeapp.data.local.entities.Medication
import com.android.app.mvvmcomposeapp.data.local.entities.User
import com.android.app.mvvmcomposeapp.data.local.entities.toMedicationItem
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import com.android.app.mvvmcomposeapp.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val medicationRepository: MedicationRepository,
    private val userDao: UserDao,
    private val medicationDao: MedicationDao

) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _medications = MutableStateFlow<List<MedicationItem>>(emptyList())
    val medications: StateFlow<List<MedicationItem>> = _medications

    val greetingMessage: String = TimeUtils.getGreetingMessage()

    init {
        viewModelScope.launch {
            _user.value = userDao.getUser()

            val localMedications = getLocalMedications()
            if (localMedications.isEmpty()) {
                fetchAndSaveMedications()
            } else {
                _medications.value = localMedications.toMedicationItem()
            }
        }
    }

    private suspend fun getLocalMedications(): List<Medication> {
        return medicationDao.getMedications()
    }

    private suspend fun fetchAndSaveMedications() {
        try {
            val result = medicationRepository.getMedications()
            _medications.value = result
            saveMedicationsLocally(result)
        } catch (e: Exception) {
            Log.e("HomeViewModel", e.message.toString())
        }
    }

    private suspend fun saveMedicationsLocally(medications: List<MedicationItem>) {
        val localMedications = medications.map { item ->
            Medication(
                id = Random.nextInt(),
                name = item.name,
                dose = item.dose ?: "",
                strength = item.strength
            )
        }
        medicationDao.insertMedications(localMedications)
    }
}