package com.android.app.mvvmcomposeapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val medicationRepository: MedicationRepository
) : ViewModel() {

    private val _medications = MutableStateFlow<List<MedicationItem>>(emptyList())
    val medications: StateFlow<List<MedicationItem>> = _medications

    init {
        fetchMedications()
    }

    private fun fetchMedications() {
        viewModelScope.launch {
            val result = medicationRepository.getMedications()
            _medications.value = result
        }
    }

    fun getMedicationById(medicationId: String): MedicationItem? {
        return _medications.value.find { it.name == medicationId }
    }
}