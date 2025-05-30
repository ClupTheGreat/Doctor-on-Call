package com.iiser.doctor_on_call.presentation.auth

import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

data class AuthUIState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)