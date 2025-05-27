package com.iiser.doctor_on_call.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnosis_results")
data class DiagnosisResultsDatabase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val diseaseName: String,
    val diseaseTreatment: String
)