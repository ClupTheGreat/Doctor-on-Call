package com.iiser.doctor_on_call.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iiser.doctor_on_call.data.model.Converters
import com.iiser.doctor_on_call.data.model.DiagnosisResultsDao
import com.iiser.doctor_on_call.data.model.DiagnosisResultsDatabase

@Database(entities = [DiagnosisResultsDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diagnosisResultsDao(): DiagnosisResultsDao
}