package com.android.app.mvvmcomposeapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.app.mvvmcomposeapp.data.remote.models.MedicationItem
import com.android.app.mvvmcomposeapp.ui.NativeLoader
import com.android.app.mvvmcomposeapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState()
    val medications by viewModel.medications.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        NativeLoader()
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = viewModel.greetingMessage,
                style = MaterialTheme.typography.displayMedium
            )
            user?.let {
                Text(
                    text = "Welcome, ${it.username}",
                    style = MaterialTheme.typography.displayMedium
                )
            } ?: run {
                Text(text = "Loading...", style = MaterialTheme.typography.displayMedium)
            }
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
}

@Composable
fun MedicationCard(medication: MedicationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medication.name}")
            Text(text = "Dose: ${medication.dose ?: "N/A"}")
            Text(text = "Strength: ${medication.strength}")
        }
    }
}