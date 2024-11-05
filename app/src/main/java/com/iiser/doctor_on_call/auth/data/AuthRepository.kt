package com.iiser.doctor_on_call.auth.data

import com.iiser.doctor_on_call.auth.domain.login.UserRole
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

    override fun getUserData(): List<Any> {
       val gatheredData: List<Any>
//       TODO: Add a query to get data from backend with user data, for now using test data
       val username = "TestUser"
        val user_id = "0001"
        val userRole: UserRole = UserRole.USER

        gatheredData = mutableListOf<Any>(username, user_id, userRole)
       return gatheredData
    }

    fun testFunction():String {
        return "Test Complete"
    }

}