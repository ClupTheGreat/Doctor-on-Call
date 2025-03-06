package com.iiser.doctor_on_call.data.model

data class DiagnosisResultItemModel (
    val id: Int,
    val date: String,
    val diseaseName: String,
    val diseaseTreatment: String
)