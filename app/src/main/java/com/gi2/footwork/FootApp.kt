package com.gi2.footwork

import android.app.Application
import coil3.*
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.google.android.gms.maps.MapsInitializer
import com.google.android.libraries.places.api.Places
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootApp : Application(), SingletonImageLoader.Factory {
  override fun onCreate() {
    super.onCreate()

    MapsInitializer.initialize(this)
    Places.initialize(this, BuildConfig.MAPS_API_KEY)
    FirebaseApp.initializeApp(this)
  }

  override fun newImageLoader(
    context: PlatformContext,
  ): ImageLoader = ImageLoader.Builder(context)
    .crossfade(true)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .diskCachePolicy(CachePolicy.ENABLED)
    .logger(DebugLogger())
    .build()
}