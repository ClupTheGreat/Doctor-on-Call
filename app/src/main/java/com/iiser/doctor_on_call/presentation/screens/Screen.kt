package com.iiser.doctor_on_call.presentation.screens

sealed class Screen(val route: String) {
    object Auth : Screen("auth_screen")
    object Dashboard : Screen("dashboard_screen")
}