package com.gi2.footwork.common.di

import com.gi2.footwork.domain.repositories.AuthRepository
import com.gi2.footwork.domain.usecases.auth.GetUserUseCase
import com.gi2.footwork.domain.usecases.auth.SignInUseCase
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
}