package com.iiser.doctor_on_call.presentation.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.data.repository.IResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: IResultRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUIState())
    val uiState = _uiState.asStateFlow()

    private val _pastDiagnoses = mutableStateListOf<DiagnosisResultItemModel>()
    val pastDiagnoses: List<DiagnosisResultItemModel> = _pastDiagnoses

    init {
        loadResults()
    }

    private fun loadResults() {
        viewModelScope.launch {
            val results = repository.getResults()
            _pastDiagnoses.clear()
            _pastDiagnoses.addAll(results)
        }
    }

}