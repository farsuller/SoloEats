
plugins {
    alias (libs.plugins.android.application) apply false
    alias (libs.plugins.android.library) apply false
    alias (libs.plugins.kotlin.android) apply false
    alias (libs.plugins.hilt) apply false
    alias(libs.plugins.devtool.ksp) apply false
    alias (libs.plugins.gms.google.services) apply false
    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.spotless) apply false
}


buildscript {
    dependencies {
        classpath(libs.spotless)
    }
}


subprojects {
    afterEvaluate {
        project.apply("${project.rootDir}/spotless.gradle")
    }
}
