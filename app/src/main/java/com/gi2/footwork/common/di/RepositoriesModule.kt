package com.gi2.footwork.common.di

import com.gi2.footwork.data.repositories.AuthRepositoryImpl
import com.gi2.footwork.data.repositories.PreferencesRepositoryImpl
import com.gi2.footwork.domain.repositories.AuthRepository
import com.gi2.footwork.domain.repositories.PreferencesRepository
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