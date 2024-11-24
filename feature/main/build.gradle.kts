plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.21"
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.solodemo.main"
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation (libs.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation (libs.material.icons.extended)
    implementation(libs.coil.compose)
    implementation(libs.orbital)
    implementation (libs.swipe)
    implementation (libs.gson)
    implementation (libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.lottie.compose)

    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.bundles.bundle.hilt)

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.common.components)
    implementation(projects.core.ui)
    implementation(projects.core.util)
    implementation(projects.core.supabase)
}