package com.iiser.doctor_on_call.presentation.screens

import kotlinx.serialization.json.Json
import android.net.Uri
import kotlinx.serialization.Serializable
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Dashboard : Screen("dashboard")
    object QuizPage : Screen("quiz_page")
    object ResultsPage : Screen("results_page/{diagnosisResult}") {
        fun createRoute(diagnosisResult: DiagnosisResultItemModel): String {
            val jsonString = Uri.encode(Gson().toJson(diagnosisResult))
            return "results_page/$jsonString"
        }
    }
    object BodySelectPage : Screen("body_select")
}