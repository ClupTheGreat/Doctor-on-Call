package com.iiser.doctor_on_call.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiagnosisResultsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResult(result: DiagnosisResultsDatabase)

    @Query("SELECT * FROM diagnosis_results")
    suspend fun getAllResults(): List<DiagnosisResultsDatabase>

    @Query("DELETE FROM diagnosis_results WHERE id = :id")
    suspend fun deleteResultById(id: Int)
}