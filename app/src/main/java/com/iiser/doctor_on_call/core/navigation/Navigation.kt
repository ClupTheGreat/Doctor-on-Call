package com.iiser.doctor_on_call.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iiser.doctor_on_call.auth.presentation.login.ui.LoginScreen
import com.iiser.doctor_on_call.auth.presentation.signup.ui.SignUpScreen
import com.iiser.doctor_on_call.core.MainScreen
import com.iiser.doctor_on_call.core.screen.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            MainScreen {
                LoginScreen(navController = navController,
                    onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route)
                })
            }
        }
        composable(route = Screen.SignUpScreen.route){
            MainScreen {
                SignUpScreen(navController = navController)
            }
        }
    }
}
