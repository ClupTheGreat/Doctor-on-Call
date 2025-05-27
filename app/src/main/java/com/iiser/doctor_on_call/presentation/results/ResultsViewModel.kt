package com.iiser.doctor_on_call.presentation.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.data.repository.IResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: IResultRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUIState())
    val uiState: StateFlow<ResultsUIState> = _uiState.asStateFlow()

    fun deleteResult(id: Int, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isDeleting = true, error = null)

                repository.deleteResultById(id)

                _uiState.value = _uiState.value.copy(
                    isDeleting = false,
                    deleteSuccess = true
                )

                onSuccess()

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isDeleting = false,
                    error = e.message ?: "Failed to delete result"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun resetDeleteSuccess() {
        _uiState.value = _uiState.value.copy(deleteSuccess = false)
    }
}