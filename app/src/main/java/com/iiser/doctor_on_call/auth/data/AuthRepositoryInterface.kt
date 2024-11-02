package com.iiser.doctor_on_call.auth.data

interface AuthRepositoryInterface {
    suspend fun authorizeLogin() : Result<Unit>
    suspend fun authorizeAccountCreation() : Result<Unit>

    suspend fun authorizeAccountDeletion() : Result<Unit>
}