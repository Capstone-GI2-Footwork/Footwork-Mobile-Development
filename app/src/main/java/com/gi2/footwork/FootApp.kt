package com.gi2.footwork

import android.app.Application
import com.google.android.gms.maps.MapsInitializer
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootApp : Application(){
    override fun onCreate() {
        super.onCreate()

        MapsInitializer.initialize(applicationContext)
//        Places.initialize(this, BuildConfig.MAPS_API_KEY)
        FirebaseApp.initializeApp(this)

    }
}
