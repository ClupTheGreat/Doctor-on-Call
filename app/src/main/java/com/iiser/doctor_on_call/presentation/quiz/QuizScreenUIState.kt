package com.iiser.doctor_on_call.presentation.quiz

import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

data class QuizScreenUIState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionsPerQuestion: Map<Int, Set<String>> = emptyMap(),
    val isComplete: Boolean = false,
    val error: String? = null,
    val dataForResultsPage: DiagnosisResultItemModel? = null
) {
    val allSelectedSymptoms: List<String>
        get() = selectedOptionsPerQuestion.values.flatten()
}