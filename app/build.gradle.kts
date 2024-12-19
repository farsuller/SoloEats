import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.devtool.ksp)
    alias(libs.plugins.firebase.crashlytics)
    id("kotlin-parcelize")
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
    namespace = ProjectConfig.NAMESPACE
    compileSdk = ProjectConfig.COMPILE_SDK

    val isGenerateBuild = ProjectConfig.GENERATE_LOCAL_ARCHIVE
    val configVersionCode = ProjectConfig.VERSION_CODE
    val configMajorVersion = ProjectConfig.MAJOR_VERSION
    val configMinorVersion = ProjectConfig.MINOR_VERSION
    val configPatchVersion = ProjectConfig.PATCH_VERSION
    val appName = ProjectConfig.APP_FILENAME

    defaultConfig {
        applicationId = ProjectConfig.APPLICATION_ID
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK
        versionCode = 10
        versionName = "2.0.0"

        if (isGenerateBuild) {
            versionCode = configVersionCode
            versionName = "${configMajorVersion}.${configMinorVersion}.${configPatchVersion}"

            applicationVariants.all {
                base.archivesName.set("$appName-${buildType.name}-$versionCode-$versionName")
            }
        }
    }

    applicationVariants.all {
        base.archivesName.set("${ProjectConfig.APP_FILENAME}-${buildType.name}-$versionCode-$versionName")
    }

    if (isGenerateBuild) {
        signingConfigs {
            register("release") {
                storeFile = file("keystore/soloeats.jks")
                storePassword = keystoreProperties["releaseStorePassword"].toString()
                keyAlias = keystoreProperties["releaseKeyAlias"].toString()
                keyPassword = keystoreProperties["releaseKeyPassword"].toString()
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            isMinifyEnabled = false
        }

        release {
            if (isGenerateBuild) signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
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
        buildConfig = true
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
    implementation(libs.navigation.compose)

    implementation(libs.splash.api)

    //Room
    implementation(libs.bundles.bundle.room)
    ksp(libs.androidx.room.compiler)

    //Hilt
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.bundles.bundle.ktor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(projects.feature.auth)
    implementation(projects.feature.main)
    implementation(projects.core.components)
    implementation(projects.core.database)
    implementation(projects.core.network)
}

