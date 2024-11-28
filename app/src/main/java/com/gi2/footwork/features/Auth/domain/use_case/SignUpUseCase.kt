package com.gi2.footwork.features.Auth.domain.use_case

import com.gi2.footwork.common.di.IoDispatcher
import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.common.helper.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class SignUpParams(
  val fullName: String,
  val email: String,
  val password: String,
)

class SignUpUseCase @Inject constructor(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val authRepo: AuthRepository,
) : UseCase<SignUpParams, Unit>(dispatcher) {

  override suspend fun execute(params: SignUpParams): Result<Unit> {
    return authRepo.signUpWithEmailAndPassword(
      params.fullName,
      params.email,
      params.password
    )
  }
}