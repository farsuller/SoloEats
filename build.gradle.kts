import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias (libs.plugins.android.application) apply false
    alias (libs.plugins.android.library) apply false
    alias (libs.plugins.kotlin.android) apply false
    alias (libs.plugins.hilt) apply false
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
