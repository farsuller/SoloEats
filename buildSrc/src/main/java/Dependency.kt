import org.gradle.api.artifacts.dsl.DependencyHandler


object Versions {
    const val hilt = "2.50"
    const val hilt_navigation = "1.1.0"
}

object Dependencies {


    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltNavCompose)
    kapt(Dependencies.hiltCompiler)
}