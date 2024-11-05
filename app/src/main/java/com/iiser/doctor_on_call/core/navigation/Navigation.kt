package com.iiser.doctor_on_call.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iiser.doctor_on_call.auth.presentation.login.ui.LoginScreen
import com.iiser.doctor_on_call.core.MainScreen
import com.iiser.doctor_on_call.core.screen.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            MainScreen { LoginScreen(navController = navController) }
        }
    }
}


@Preview
@Composable
fun previewSignUpScreen(){

}
