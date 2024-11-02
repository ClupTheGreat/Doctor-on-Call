package com.iiser.doctor_on_call.core.data

data class DoctorOnCallUiState(

    val isSignedIn: Boolean = false,
    val profile: UserProfile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class UserProfile(
    val userName: String? = null
)
