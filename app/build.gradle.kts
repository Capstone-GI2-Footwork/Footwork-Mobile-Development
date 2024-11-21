import java.util.Properties

val keystoreFile = rootProject.file("app/secrets.properties")
val properties = Properties()
if (keystoreFile.exists()) {
  keystoreFile.inputStream().use {
    properties.load(it)
  }
}

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.googleKsp)
  alias(libs.plugins.daggerHiltPlugin)
  alias(libs.plugins.googleMapsPlatform)
  id("kotlin-parcelize")
  alias(libs.plugins.googleGmsGoogleServices)
  alias(libs.plugins.jetbrains.kotlin.serialization)
}

//composeCompiler {
//    enableStrongSkippingMode = true
//    reportsDestination = layout.buildDirectory.dir("compose_compiler")
//}

android {
  namespace = "com.gi2.footwork"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.gi2.footwork"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }

    fun loadConfig(type: String, key: String, defaultValue: String) {
      val value = properties.getProperty(key) ?: defaultValue
      manifestPlaceholders[key] = value
      buildConfigField(type, key, "\"$value\"")
    }

    loadConfig("String", "MAPS_API_KEY", "")
    loadConfig("String", "FOOTWORK_API_BASE_URL", "")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  buildFeatures {
    buildConfig = true
    compose = true
  }
//  composeOptions {
//    kotlinCompilerExtensionVersion = "1.5.1"
//  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.transportation.consumer)
  implementation(libs.androidx.ui.text.google.fonts)
  implementation(libs.androidx.compose.material.iconsExtended)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.appcompat.resources)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  // view model compose
  implementation(libs.androidx.lifecycle.compose.viewmodel)
  implementation(libs.androidx.lifecycle.compose.runtime)
  // dagger hilt
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  ksp(libs.androidx.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)
  // navigation compose
  implementation(libs.androidx.navigation.compose)
  // maps
  implementation(libs.maps.compose)
  implementation(libs.maps.compose.utils)
  implementation(libs.maps.compose.widgets)
  implementation(libs.play.services.maps)
  implementation(libs.play.services.location)
  implementation(libs.android.maps.utils)
  // permission library
  implementation(libs.accompanist.permissions)
  // Splash API
  implementation(libs.androidx.core.splashscreen)
  // shared preferences
  implementation(libs.androidx.datastore.preferences)
  // socket io
  implementation(libs.socket.io.client.v200)
  // gson
  implementation(libs.gson)
  // places api
  implementation(libs.places)
  implementation(libs.firebase.messaging)
  implementation(platform(libs.firebase.bom))
  // markdown reader
  implementation(libs.compose.markdown)
  // kotlinx-serialization
  implementation(libs.kotlinx.serialization.json)
  // google mdc components
  implementation(libs.google.android.material)
  //  okhttp
  implementation(platform(libs.okhttp.bom))
  implementation(libs.okhttp)
  implementation(libs.okhttp.loggingInterceptor)
  //  retrofit
  implementation(libs.retrofit)
  implementation(libs.retrofit.serialization)
  //  skydoves sandwich
  implementation(libs.sandwich)
  implementation(libs.sandwich.retrofit)
  implementation(libs.sandwich.retrofit.serialization)
  //  coil3
  implementation(libs.coil.compose)
  implementation(libs.coil.okhttp)
  //  skydoves landscapist
  implementation(libs.landscapist.coil)
  //  orbit mvi
  implementation(libs.orbitMvi.core)
  implementation(libs.orbitMvi.viewmodel)
  implementation(libs.orbitMvi.compose)
  testImplementation(libs.orbitMvi.test)
}