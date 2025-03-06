package com.iiser.doctor_on_call.data.model

data class DiseaseTreatmentModel(
    val diseaseName: String,
    val diseaseSymptoms: List<String>,
    val diseaseTreatment: List<String>
)