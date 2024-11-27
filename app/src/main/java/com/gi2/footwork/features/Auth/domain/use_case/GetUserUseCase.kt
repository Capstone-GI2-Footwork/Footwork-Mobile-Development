package com.gi2.footwork.features.Auth.domain.use_case

import com.gi2.footwork.common.di.IoDispatcher
import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.common.helper.UseCase
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