package com.iiser.doctor_on_call.domain.repository

import com.iiser.doctor_on_call.data.model.User
import com.iiser.doctor_on_call.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun registerUser(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }
}