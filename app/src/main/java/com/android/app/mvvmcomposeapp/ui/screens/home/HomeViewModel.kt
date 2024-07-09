package com.android.app.mvvmcomposeapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.data.remote.repositories.MedicationRepository
import com.android.app.mvvmcomposeapp.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val medicationRepository: MedicationRepository
) : ViewModel() {

    private val _medications = MutableStateFlow<List<MedicationItem>>(emptyList())
    val medications: StateFlow<List<MedicationItem>> = _medications

    val username: String = "User" // Replace with actual user data
    val greetingMessage: String = TimeUtils.getGreetingMessage()

    init {
        fetchMedications()
    }

    private fun fetchMedications() {
        viewModelScope.launch {
            val result = medicationRepository.getMedications()
            _medications.value = result
        }
    }
}