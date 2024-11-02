package com.iiser.doctor_on_call.core.data

data class DoctorOnCallUiState(

    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: List<AppErrors> = listOf(AppErrors.NO_ERROR),
    val userName: String? = null,
    val profile: UserProfile? = null,
)

data class UserProfile(
    val userId: String? = null,
    val userRole: UserRole = UserRole.NO_LOGIN
)

enum class UserRole {
    DOCTOR,
    USER,
    ADMIN,
    NO_LOGIN,
    EXAMPLE_ROLE
}

enum class AppErrors{
    NO_ERROR,
    SHORT_PASSWORD,
    UNSAFE_PASSWORD,
    NO_PASSWORD,
    SHORT_USERNAME,
    NO_USERNAME,
    LOGIN_FAILED

}

