package com.iiser.doctor_on_call.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import android.net.Uri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.presentation.auth.AuthScreen
import com.iiser.doctor_on_call.presentation.bodySelect.BodySelectScreen
import com.iiser.doctor_on_call.presentation.dashboard.DashboardScreen
import com.iiser.doctor_on_call.presentation.quiz.QuizScreen
import com.iiser.doctor_on_call.presentation.results.ResultsScreen
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
                // onNavigateToDashboardScreen = { navController.navigate(Screen.BodySelectPage.route) }
            )
        }

        composable(Screen.Dashboard.route){
            DashboardScreen(

                onNavigateToBodySelectScreen = { navController.navigate(Screen.BodySelectPage.route) }

            )
        }

        composable(Screen.QuizPage.route){
            val selectedRegionsText = it.arguments?.getString("selectedRegionsText")
            QuizScreen(
                selectedRegionsText = selectedRegionsText,
                onBackClick = { navController.popBackStack() },
                onComplete = { resultData ->
                    val json = Uri.encode(Gson().toJson(resultData))
                    navController.navigate("results_page/$json")
                }
            )
        }



        composable(
            route = Screen.ResultsPage.route,
            arguments = listOf(
                navArgument("diagnosisResult") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("diagnosisResult")
            val diagnosisResult = jsonString?.let {
                Gson().fromJson(Uri.decode(it), DiagnosisResultItemModel::class.java)
            }

            ResultsScreen(result = diagnosisResult)
        }

        composable(Screen.BodySelectPage.route){
            BodySelectScreen(showHitboxes = false, controller = navController)


        }
    }

}