package com.iiser.doctor_on_call.auth.presentation.signup.viewmodel

import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.ViewModel
import com.iiser.doctor_on_call.auth.domain.login.DoctorOnCallUiState
import com.iiser.doctor_on_call.auth.domain.signup.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel(){
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiSate: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String){
        _uiState.update { currentState ->
            currentState.copy(username = username)
        }
    }

    fun onEmailChange(email: String){
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun onPasswordChange(password: String){
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun onPhoneNumber(phone_number: String){
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = phone_number)
        }
    }
}