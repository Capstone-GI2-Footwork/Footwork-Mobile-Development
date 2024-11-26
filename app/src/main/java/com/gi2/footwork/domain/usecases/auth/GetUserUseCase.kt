package com.gi2.footwork.domain.usecases.auth

import com.gi2.footwork.common.di.IoDispatcher
import com.gi2.footwork.domain.repositories.AuthRepository
import com.gi2.footwork.domain.usecases.common.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val authRepo: AuthRepository,
) : UseCase<Unit, Boolean>(dispatcher) {
  override suspend fun execute(params: Unit): Result<Boolean> {
    return Result.success(authRepo.getAuth())
  }
}