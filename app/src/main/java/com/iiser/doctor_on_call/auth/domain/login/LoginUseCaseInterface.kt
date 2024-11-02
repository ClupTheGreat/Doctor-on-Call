package com.iiser.doctor_on_call.auth.domain.login

interface LoginUseCaseInterface {
    suspend fun login(username: String, password: String) : Result<Unit>
}