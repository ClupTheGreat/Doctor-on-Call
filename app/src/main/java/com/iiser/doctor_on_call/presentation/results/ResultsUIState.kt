package com.iiser.doctor_on_call.presentation.results

data class ResultsUIState(
    val isLoading: Boolean = false,
    val isDeleting: Boolean = false,
    val error: String? = null,
    val deleteSuccess: Boolean = false
)
