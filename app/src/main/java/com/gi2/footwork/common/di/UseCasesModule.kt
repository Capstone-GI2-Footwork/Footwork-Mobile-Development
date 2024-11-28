package com.gi2.footwork.common.di

import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.features.Auth.domain.use_case.GetUserUseCase
import com.gi2.footwork.features.Auth.domain.use_case.SignInUseCase
import com.gi2.footwork.features.Auth.domain.use_case.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
  @Provides
  @Singleton
  fun provideGetUserUseCase(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    authRepository: AuthRepository,
  ) = GetUserUseCase(
    dispatcher = ioDispatcher,
    authRepo = authRepository
  )

  @Provides
  @Singleton
  fun provideSignInUseCase(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    authRepository: AuthRepository,
  ) = SignInUseCase(
    dispatcher = ioDispatcher,
    authRepo = authRepository
  )

  @Provides
  @Singleton
  fun provideSignUpUseCase(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    authRepository: AuthRepository,
  ) = SignUpUseCase(
    dispatcher = ioDispatcher,
    authRepo = authRepository
  )
}