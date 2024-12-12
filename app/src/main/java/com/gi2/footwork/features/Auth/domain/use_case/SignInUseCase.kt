package com.gi2.footwork.features.Auth.domain.use_case

import com.gi2.footwork.common.di.IoDispatcher
import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.common.helper.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class SignInParams(
  val email: String,
  val password: String,
)

class SignInUseCase @Inject constructor(
  @IoDispatcher dispatcher: CoroutineDispatcher,
  private val authRepo: AuthRepository,
) : UseCase<SignInParams, Unit>(dispatcher) {

  override suspend fun execute(params: SignInParams): Result<Unit> {
    return authRepo.signInWithEmailAndPassword(
      params.email,
      params.password
    )
  }
}