package com.iiser.doctor_on_call.domain.repository

import com.iiser.doctor_on_call.data.model.DiagnosisResultEntry
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.data.model.DiagnosisResultsDao
import com.iiser.doctor_on_call.data.model.DiagnosisResultsDatabase
import com.iiser.doctor_on_call.data.model.DiseaseTreatmentModel
import com.iiser.doctor_on_call.data.repository.IResultRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ResultRepository @Inject constructor(
    private val dao: DiagnosisResultsDao
) : IResultRepository {

    override suspend fun storeResult(result: DiagnosisResultItemModel) {
        val entity = DiagnosisResultsDatabase(
            id = 0, // Let Room auto-generate the ID
            date = result.date,
            diseaseName = result.diseaseName,
            diseaseTreatment = result.diseaseTreatment
        )
        dao.insertResult(entity)
    }

    override suspend fun getResults(): List<DiagnosisResultItemModel> {
        return dao.getAllResults().map {
            DiagnosisResultItemModel(
                id = it.id,
                date = it.date,
                diseaseName = it.diseaseName,
                diseaseTreatment = it.diseaseTreatment
            )
        }
    }

    override suspend fun deleteResultById(id: Int){
        dao.deleteResultById(id)
    }


}