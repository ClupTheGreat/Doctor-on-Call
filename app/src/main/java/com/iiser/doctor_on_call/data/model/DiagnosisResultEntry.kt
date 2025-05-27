package com.iiser.doctor_on_call.data.model

data class DiagnosisResultEntry(
    val id: Int,
    val model: DiseaseTreatmentModel,
    val date: String
)