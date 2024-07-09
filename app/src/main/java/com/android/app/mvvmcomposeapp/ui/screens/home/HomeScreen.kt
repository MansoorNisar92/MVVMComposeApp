package com.android.app.mvvmcomposeapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val medications by viewModel.medications.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = viewModel.greetingMessage,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Welcome, ${viewModel.username}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(medications) { medication ->
                MedicationCard(medication, onClick = {
                    navController.navigate(Screen.Details.createRoute(medication.name))
                })
            }
        }
    }
}

@Composable
fun MedicationCard(medication: MedicationItem, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medication.name}")
            Text(text = "Dose: ${medication.dose ?: "N/A"}")
            Text(text = "Strength: ${medication.strength}")
        }
    }
}