//package com.iiser.doctor_on_call.di
//
//import android.content.Context
//import androidx.room.Room
//import com.iiser.doctor_on_call.data.model.DiagnosisResultsDao
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext appContext: Context // ✅ THIS LINE FIXES IT
//    ): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "doctor_on_call_db"
//        ).build()
//    }
//
//    @Provides
//    fun provideDao(db: AppDatabase): DiagnosisResultsDao {
//        return db.diagnosisResultsDao()
//    }
//}