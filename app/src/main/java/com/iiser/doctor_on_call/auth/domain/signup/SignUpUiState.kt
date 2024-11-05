package com.iiser.doctor_on_call.auth.domain.signup

data class SignUpUiState(

    val username: String? = "",
    val password: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)