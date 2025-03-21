// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt") version "1.8.10" // Make sure this matches your Kotlin version
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" // Add
}
