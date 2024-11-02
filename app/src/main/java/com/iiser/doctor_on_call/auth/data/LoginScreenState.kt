package com.iiser.doctor_on_call.auth.data

data class LoginScreenState(

    val username: String = "",
    val password: String = "",
    val isUsernameValid: Boolean= false,
    val isPasswordValid: Boolean = false,
    val isPasswordStrong: Boolean = false

)
