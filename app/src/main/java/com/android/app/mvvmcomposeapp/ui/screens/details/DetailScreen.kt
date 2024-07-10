package com.android.app.mvvmcomposeapp.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController,
    medicationId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val medication = viewModel.getMedicationById(medicationId)

    medication?.let {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Name: ${medication.name}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Dose: ${medication.dose ?: "N/A"}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Strength: ${medication.strength}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    } ?: run {
        Text(
            text = "Medication not found",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}