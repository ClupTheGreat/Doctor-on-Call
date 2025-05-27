package com.iiser.doctor_on_call.data.repository

import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

interface IResultRepository {
    suspend fun storeResult(result: DiagnosisResultItemModel)
    suspend fun getResults(): List<DiagnosisResultItemModel>
    suspend fun deleteResultById(id:Int)
}