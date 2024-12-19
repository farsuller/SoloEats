plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.devtool.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.solodemo.network"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://demoapi-pkos.onrender.com/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.bundles.bundle.ktor)

    implementation(libs.kotlinx.serialization.json)

    //Hilt
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(projects.core.components)
}