package com.iiser.doctor_on_call.auth.domain.signup

import com.iiser.doctor_on_call.auth.data.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCaseInterface {
    override suspend fun signUp(
        username: String,
        password: String,
        email: String,
        phone_number: String?
    ) {
        TODO("Not yet implemented")
    }
}