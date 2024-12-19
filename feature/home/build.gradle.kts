plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.devtool.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.solodemo.home"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.bundle.androidx.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.material3)
    implementation(libs.material)
    implementation(libs.navigation.compose)

    implementation(libs.material.icons.extended)

    implementation(libs.bundles.bundle.coil)

    implementation(libs.swipe)
    implementation(libs.gson)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.lottie.compose)

    //implementation(libs.message.bar.compose)

    debugImplementation(libs.androidx.ui.tooling)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    //Hilt
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation (libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(projects.core.components)
    implementation(projects.core.messagebar)
    implementation(projects.core.database)
    implementation(projects.core.network)

}