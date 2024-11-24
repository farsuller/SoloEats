import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias (libs.plugins.android.application) apply false
    alias (libs.plugins.android.library) apply false
    alias (libs.plugins.kotlin.android) apply false
    alias (libs.plugins.hilt) apply false
    alias(libs.plugins.devtool.ksp) apply false
    alias (libs.plugins.gms.google.services) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.spotless) apply false
}


buildscript {
    dependencies {
        classpath(libs.spotless)
    }
}


subprojects {
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf(
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
                )
            )
        }
    }
    afterEvaluate {
        project.apply("${project.rootDir}/spotless.gradle")
    }
}
