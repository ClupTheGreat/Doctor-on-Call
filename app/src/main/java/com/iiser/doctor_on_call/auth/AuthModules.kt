package com.iiser.doctor_on_call.auth

import com.iiser.doctor_on_call.auth.data.AuthRepository
import com.iiser.doctor_on_call.auth.data.AuthRepositoryInterface
import com.iiser.doctor_on_call.auth.domain.login.LoginUseCase
import com.iiser.doctor_on_call.auth.domain.login.LoginUseCaseInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModules {
    @Provides
    fun provideAuthRepository(): AuthRepositoryInterface = AuthRepository()

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCaseInterface  = LoginUseCase(authRepository)
}