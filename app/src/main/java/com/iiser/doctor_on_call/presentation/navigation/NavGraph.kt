package com.iiser.doctor_on_call.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iiser.doctor_on_call.presentation.auth.AuthScreen
import com.iiser.doctor_on_call.presentation.dashboard.DashboardScreen
import com.iiser.doctor_on_call.presentation.screens.Screen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route){
            AuthScreen(
                onNavigateToDashboardScreen = { navController.navigate(Screen.Dashboard.route) }
            )
        }
        composable(Screen.Dashboard.route){
            DashboardScreen()
        }
    }

}