package com.iiser.doctor_on_call.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.data.model.DiseaseTreatmentModel
import com.iiser.doctor_on_call.data.model.QuestionAndOptionsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(QuizScreenUIState())
    val uiState = _uiState.asStateFlow()


    //Questions List

    private val questions = listOf(
        QuestionAndOptionsModel(
            "Which of these fever-related symptoms do you have? (Select all that apply)",
            listOf("Fever", "Fever or chills", "Fever &/or chills", "Fever with night sweats")
        ),
        QuestionAndOptionsModel(
            "Which of these respiratory symptoms do you have? (Select all that apply)",
            listOf("Runny nose", "Cough with greenish-yellow mucus", "Shortness of breath", "Coughing")
        ),
        QuestionAndOptionsModel(
            "Which of these throat-related symptoms do you have? (Select all that apply)",
            listOf("Sore throat", "Painful swallowing", "Occasionally throat pain", "White patches on tonsils")
        ),
        QuestionAndOptionsModel(
            "Which of these general body symptoms do you have? (Select all that apply)",
            listOf("Malaise (general discomfort)", "Severe body ache", "Body ache", "Fatigue", "Loss of weight")
        ),
        QuestionAndOptionsModel(
            "Which of these digestive symptoms do you have? (Select all that apply)",
            listOf("Nausea", "Abdominal pain", "Loss of appetite")
        ),
        QuestionAndOptionsModel(
            "Which of these other symptoms do you have? (Select all that apply)",
            listOf("Skin rash or red spots", "Loss of taste or smell", "Swollen tender lymph nodes")
        )
    )

    // Diseases

    val diseases = listOf(
        DiseaseTreatmentModel(
            "Common Cold",
            listOf("Fever", "Malaise", "Runny nose", "Occasionally throat pain"),
            listOf("Analgesic", "Antipyretic", "Gargles")
        ),
        DiseaseTreatmentModel(
            "Influenza",
            listOf("Fever", "Sore throat", "Body ache", "Fatigue"),
            listOf("Rest", "Fluids", "Analgesics", "Decongestant")
        ),
        DiseaseTreatmentModel(
            "Covid-19",
            listOf("Fever or chills", "Sore throat", "Loss of taste or smell", "Fatigue"),
            listOf("Rest", "Fluids", "Antipyretics", "Safe distancing", "Mask")
        ),
        DiseaseTreatmentModel(
            "Streptococcus Throat Infection",
            listOf("Painful swallowing", "Fever", "Swollen tender lymph nodes", "White patches on tonsils"),
            listOf("Analgesic", "Antipyretic", "Gargles", "Throat lozenges")
        ),
        DiseaseTreatmentModel(
            "Pneumonia",
            listOf("Fever &/or chills", "Cough with greenish yellow mucus", "Shortness of breath", "Loss of appetite"),
            listOf("Antipyretic", "Antibiotic", "Rest", "Chest X-ray")
        ),
        DiseaseTreatmentModel(
            "Dengue",
            listOf("Fever &/or chills", "Severe body ache", "Nausea", "Skin rash or red spots"),
            listOf("Fluids", "Antipyretic", "Laboratory tests", "Rest")
        ),
        DiseaseTreatmentModel(
            "Hepatitis A",
            listOf("Fatigue", "Nausea", "Abdominal pain", "Loss of appetite"),
            listOf("Rest", "Fluids", "Avoid alcohol")
        ),
        DiseaseTreatmentModel(
            "Tuberculosis",
            listOf("Fever with night sweats", "Loss of weight", "Loss of appetite", "Coughing"),
            listOf("Chest X-ray", "(mHealthcare) Chest physician")
        )
    )

    fun toggleOption(option: String) {
        val currentQuestionIndex = _uiState.value.currentQuestionIndex
        val currentSelectedOptions = _uiState.value.selectedOptionsPerQuestion[currentQuestionIndex] ?: emptySet()

        val updatedOptions = if (currentSelectedOptions.contains(option)) {
            currentSelectedOptions - option
        } else {
            currentSelectedOptions + option
        }

        _uiState.value = _uiState.value.copy(
            selectedOptionsPerQuestion = _uiState.value.selectedOptionsPerQuestion +
                    (currentQuestionIndex to updatedOptions)
        )
    }

    fun onNextQuestion() {
        if (_uiState.value.currentQuestionIndex + 1 < questions.size) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = _uiState.value.currentQuestionIndex + 1
            )
        } else {
            _uiState.value = _uiState.value.copy(isComplete = true)
            analyzeSymptomsAndSaveResult()
        }
    }

    fun onPreviousQuestion() {
        if (_uiState.value.currentQuestionIndex > 0) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = _uiState.value.currentQuestionIndex - 1
            )
        }
    }

    private fun analyzeSymptomsAndSaveResult() {
        viewModelScope.launch {
            try {
                val matchedDiseases = findTopDiseases(_uiState.value.allSelectedSymptoms)
                val result = createDiagnosisResult(matchedDiseases)
                _uiState.value = _uiState.value.copy(
                    dataForResultsPage = result
                )
                // TODO When diagnosis Repository is created
//                diagnosisRepository.saveDiagnosisResult(result)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }

        }

    }

    private fun findTopDiseases(symptoms: List<String>): List<DiseaseTreatmentModel> {
        return diseases
            .map { disease ->
                val matchCount = disease.diseaseSymptoms.count { it in symptoms }
                Pair(disease, matchCount)
            }
            .sortedByDescending { it.second }
            .take(2)
            .map { it.first }
    }

    private fun createDiagnosisResult(
        matchedDiseases: List<DiseaseTreatmentModel>
    ): DiagnosisResultItemModel {
        val combinedDiseases = matchedDiseases.joinToString(" or ") { it.diseaseName }
        val combinedTreatments = matchedDiseases
            .flatMap { it.diseaseTreatment }
            .distinct()
            .joinToString("\n") { "• $it" }

        return DiagnosisResultItemModel(
            id = System.currentTimeMillis().toInt(),
            date = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date()),
            diseaseName = combinedDiseases,
            diseaseTreatment = combinedTreatments
        )
    }

    fun getCurrentQuestion(): QuestionAndOptionsModel? {
        return questions.getOrNull(_uiState.value.currentQuestionIndex)
    }



}