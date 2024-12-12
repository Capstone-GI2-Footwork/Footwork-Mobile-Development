package com.gi2.footwork.common.di

import com.gi2.footwork.features.Auth.data.repository.AuthRepositoryImpl
import com.gi2.footwork.common.repositoryImpl.PreferencesRepositoryImpl
import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.common.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
  @Binds
  @Singleton
  abstract fun bindPreferencesRepository(
    preferencesRepositoryImpl: PreferencesRepositoryImpl,
  ): PreferencesRepository

  @Binds
  @Singleton
  abstract fun bindAuthRepository(
    authRepositoryImpl: AuthRepositoryImpl,
  ): AuthRepository
}