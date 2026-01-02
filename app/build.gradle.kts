import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.plugin)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

val apiKey: String = project.rootProject.file("local.properties")
    .inputStream()
    .use { Properties().apply { load(it) } }
    .getProperty("NEWS_API_KEY") ?: ""

android {
    namespace = "com.morarafrank.dailybrief"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.morarafrank.dailybrief"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NEWS_API_KEY", "\"$apiKey\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation Compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.play.services)

    // Hilt
    implementation (libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//    kapt(libs.androidx.hilt.compiler)
    ksp (libs.hilt.android.compiler)
    ksp (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    // OkHttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // JSON Parsing
    implementation (libs.gson)
    implementation (libs.converter.gson)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    //    Moshi
    implementation (libs.squareup.moshi.kotlin)
    implementation (libs.converter.moshi)

    // Coil
    implementation (libs.coil.compose)

    // Shared Preferences
//    implementation (libs.androidx.preference.ktx)
    implementation(libs.androidx.security.crypto)


    // Room
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    ksp (libs.room.compiler)
    annotationProcessor (libs.room.compiler)

    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)

    // Saved state module for ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(libs.androidx.lifecycle.runtime.ktx.v262)


    // Annotation processor
    ksp (libs.androidx.lifecycle.compiler)

    // Lottie
    implementation(libs.lottie.compose)

    // Coil compose
    implementation(libs.coil.compose)


}