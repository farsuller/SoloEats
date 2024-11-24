import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.21"
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

val keystoreProperties: Properties by lazy {
    val properties = Properties()
    val keystorePropertiesFile = rootProject.file("keystore.properties")

    if (keystorePropertiesFile.exists()) {
        properties.load(keystorePropertiesFile.inputStream())
    } else {
        throw FileNotFoundException("Keystore properties file not found.")
    }

    properties
}

android {
    namespace = "com.solodemo.supabase"
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)

    implementation (libs.gson)
    implementation(libs.supabase.compose.auth)
    implementation(libs.supabase.gotrue.kt)
    implementation(libs.supabase.postgrest.kt)
    implementation(libs.ktor.client.cio)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.bundle.hilt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.common.components)
}