package com.iiser.doctor_on_call.core.screen

sealed class Screen(val route: String) {
    data object  LoginScreen :Screen("LoginScreen")
    data object  SignUpScreen :Screen("SignUpScreen")
}