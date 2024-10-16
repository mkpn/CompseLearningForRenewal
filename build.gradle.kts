// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("org.jetbrains.kotlinx.kover") version "0.9.0-RC"
    alias(libs.plugins.compose.compiler) apply false
}
true // Needed to make the Suppress annotation work for the plugins block