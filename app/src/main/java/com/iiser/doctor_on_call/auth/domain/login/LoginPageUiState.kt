package com.iiser.doctor_on_call.auth.domain.login

data class DoctorOnCallUiState(

    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: List<LoginPageErrors> = listOf(LoginPageErrors.NO_ERROR),
    var userName: String? = null,
    var profile: UserProfile? = null,
)

data class UserProfile(
    val userId: String? = null,
    val userRole: Any = UserRole.NO_LOGIN
)

enum class UserRole {
    DOCTOR,
    USER,
    ADMIN,
    NO_LOGIN,
    EXAMPLE_ROLE
}

enum class LoginPageErrors{
    NO_ERROR,
    SHORT_PASSWORD,
    UNSAFE_PASSWORD,
    NO_PASSWORD,
    SHORT_USERNAME,
    NO_USERNAME,
    LOGIN_FAILED

}


