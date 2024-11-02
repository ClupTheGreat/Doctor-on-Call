package com.iiser.doctor_on_call.auth.domain.login

import com.iiser.doctor_on_call.auth.data.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(

    private val authRepository: AuthRepository

) : LoginUseCaseInterface {
    override suspend fun login(username: String, password: String): Result<Unit> {
        TODO("Not yet implemented")

    }

    fun testLoginUseCaseFunction(username: String, password: String): String {
        return authRepository.testFunction()
    }
}