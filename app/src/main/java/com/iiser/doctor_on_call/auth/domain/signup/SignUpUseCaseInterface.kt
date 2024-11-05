package com.iiser.doctor_on_call.auth.domain.signup

interface SignUpUseCaseInterface {
    suspend fun signUp(username: String,
                       password: String,
                       email: String,
                       phone_number: String?
    )
}