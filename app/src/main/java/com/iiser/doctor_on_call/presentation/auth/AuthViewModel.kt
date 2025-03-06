package com.iiser.doctor_on_call.presentation.auth

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.core.Resource
import com.iiser.doctor_on_call.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState = _uiState.asStateFlow()

    // Updater Functions

    fun updateEmail(email: String){
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String){
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }


    fun updateConfirmPassword(confirmPassword: String){
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }
    fun updateRememberMe(rememberMe: Boolean){
        _uiState.update {
            it.copy(
                rememberMe = rememberMe
            )
        }
    }

    // Main Function

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch { onSuccess() }
        //TODO Implement login function
    }

    fun changeTab() {

    }

    fun register(onSuccess: () -> Unit){
        viewModelScope.launch {
            onSuccess()
        }
    }

    fun forgotPassword() {

    }


}