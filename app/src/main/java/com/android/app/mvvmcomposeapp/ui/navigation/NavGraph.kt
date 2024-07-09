package com.android.app.mvvmcomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.app.mvvmcomposeapp.ui.screens.details.DetailScreen
import com.android.app.mvvmcomposeapp.ui.screens.home.HomeScreen
import com.android.app.mvvmcomposeapp.ui.screens.login.LoginScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Details : Screen("details/{medicationId}") {
        fun createRoute(medicationId: String) = "details/$medicationId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Details.route) { backStackEntry ->
            val medicationId =
                backStackEntry.arguments?.getString("medicationId") ?: return@composable
            DetailScreen(navController, medicationId)
        }
    }
}