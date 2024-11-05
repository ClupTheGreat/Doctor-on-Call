package com.iiser.doctor_on_call.auth.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.auth.domain.login.LoginUseCase
import com.iiser.doctor_on_call.auth.domain.login.DoctorOnCallUiState
import com.iiser.doctor_on_call.auth.domain.login.LoginPageErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(DoctorOnCallUiState())
    val uiState: StateFlow<DoctorOnCallUiState> = _uiState.asStateFlow()

    /*  TODO
    *   Properly setup once database is active
    *   We require to change _uiState to have username with userId, userRole etc
    *   We update _uiState once we know the login is successful from AuthRepository
    *   For now we just leave it as null till mockup is made
    */
    fun onUsernameChanged(username: String) {
        // TODO
        // Add More Conditions like there is on onPasswordChanged()
        _uiState.value = _uiState.value.copy(userName = username)
    }

    fun onPasswordChanged(password: String) {
        if (password.length < 5){
            _uiState.value.errorMessage.toMutableList().apply { add(LoginPageErrors.SHORT_PASSWORD) }
        } else if (password == "12345") {
            _uiState.value.errorMessage.toMutableList().apply { add(LoginPageErrors.UNSAFE_PASSWORD) }
        } else if (password.length > 5) {
            if (LoginPageErrors.SHORT_PASSWORD in _uiState.value.errorMessage){
                _uiState.value.errorMessage.toMutableList().remove(LoginPageErrors.SHORT_PASSWORD)
            }
        } else if (password != "12345") {
            if (LoginPageErrors.SHORT_PASSWORD in _uiState.value.errorMessage){
                _uiState.value.errorMessage.toMutableList().remove(LoginPageErrors.SHORT_PASSWORD)
            }
        }
    }

    fun login(username: String, password: String ){

        if (_uiState.value.errorMessage == listOf(LoginPageErrors.NO_ERROR)){
            viewModelScope.launch {
                _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = listOf(
                    LoginPageErrors.NO_ERROR))

                val result = loginUseCase.testLoginUseCaseFunction(username, password)
                println(result)
// TODO FIX THIS FOR REAL, CURRENT CODE ABOVE IS TESTING
//                _uiState.value = if (result.isSuccess) {
//                    _uiState.value.copy(
//                        isLoading = false,
//                        isSignedIn = true,
//                        userName = username,
//                        profile = UserProfile(userId = "GetUserIDFromRepository", userRole = UserRole.EXAMPLE_ROLE),
//                        errorMessage = listOf(AppErrors.NO_ERROR)
//                    )
//                } else {
//                    _uiState.value.copy(
//                        isLoading = false,
//                        isSignedIn = false,
//                        userName = null,
//                        profile = null,
//                        errorMessage = listOf(AppErrors.LOGIN_FAILED)
//                    )
//                }
            }



        }

    }

}