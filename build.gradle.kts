// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.21"
    id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false
    alias(libs.plugins.compose.compiler) apply false


}