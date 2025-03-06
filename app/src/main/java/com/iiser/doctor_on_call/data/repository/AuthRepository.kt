package com.iiser.doctor_on_call.data.repository

import com.iiser.doctor_on_call.data.model.User

interface AuthRepository {
    suspend fun registerUser(email: String, password: String): Result<User>
    suspend fun loginUser(email: String, password: String): Result<User>
}