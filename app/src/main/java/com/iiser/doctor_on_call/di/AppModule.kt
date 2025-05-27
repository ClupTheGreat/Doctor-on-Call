package com.iiser.doctor_on_call.di

import android.content.Context
import androidx.room.Room
import com.iiser.doctor_on_call.data.model.DiagnosisResultsDao
import com.iiser.doctor_on_call.data.repository.IResultRepository
import com.iiser.doctor_on_call.domain.repository.ResultRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "diagnosis_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase): DiagnosisResultsDao = db.diagnosisResultsDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindResultRepository(
        impl: ResultRepository
    ): IResultRepository
}