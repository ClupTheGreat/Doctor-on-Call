package com.iiser.doctor_on_call.core.data

import kotlinx.coroutines.flow.MutableStateFlow

data class DoctorOnCallUiState(

    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: List<AppErrors> = listOf(AppErrors.NO_ERROR),
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

enum class AppErrors{
    NO_ERROR,
    SHORT_PASSWORD,
    UNSAFE_PASSWORD,
    NO_PASSWORD,
    SHORT_USERNAME,
    NO_USERNAME,
    LOGIN_FAILED

}

fun DoctorOnCallUiStateBuilder(dataList: List<Any>): MutableStateFlow<DoctorOnCallUiState>{
    val uiStateBuilt = MutableStateFlow(DoctorOnCallUiState())
    val userName: String? = dataList.[0].toString()
    val user_id: String? = dataList[1].toString()
    val userrole = dataList[2]
    val userprofile: UserProfile = UserProfile(userId = user_id, userRole = userrole)

    uiStateBuilt.value.userName = userName
    uiStateBuilt.value.profile = userprofile

    return uiStateBuilt
}

