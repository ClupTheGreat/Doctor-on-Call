package com.iiser.doctor_on_call.auth.data

import javax.inject.Inject

class AuthRepository @Inject constructor() : AuthRepositoryInterface {
    override suspend fun authorizeLogin(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun authorizeAccountCreation(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun authorizeAccountDeletion(): Result<Unit> {
        TODO("Not yet implemented")
    }

    fun testFunction():String {
        return "Test Complete"
    }


}