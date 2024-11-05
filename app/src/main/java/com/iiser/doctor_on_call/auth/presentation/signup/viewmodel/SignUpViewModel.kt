package com.iiser.doctor_on_call.auth.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.iiser.doctor_on_call.core.data.DoctorOnCallUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ViewModel(){
    private val _uiState = MutableStateFlow(DoctorOnCallUiState())
    val uiSate: StateFlow<DoctorOnCallUiState> = _uiState.asStateFlow()
}