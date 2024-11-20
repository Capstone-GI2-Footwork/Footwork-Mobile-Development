package com.gi2.footwork.common.lib.http.interceptors

import com.gi2.footwork.common.di.IoDispatcher
import com.gi2.footwork.domain.repositories.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val repo: PreferencesRepository,
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val token: String? = runBlocking(
      context = dispatcher
    ) { repo.getAuthToken() }

    var request = chain.request()
    if (token != null) {
      request = request.newBuilder()
        .addHeader("Authorization", "Bearer $token")
        .build()
    }

    return chain.proceed(request)
  }
}