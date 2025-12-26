package com.example.privacy_detect_plugins.configure

import com.android.build.gradle.LibraryExtension
import com.example.privacy_detect_plugins.TraceBuildConfig
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File
internal fun LibraryExtension.libraryModule() {
    compileSdk = TraceBuildConfig.compileSdk
    buildToolsVersion = TraceBuildConfig.buildToolsVersion
    defaultConfig {
        minSdk = TraceBuildConfig.minSdk
        consumerProguardFiles.add(File("consumer-rules.pro"))
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    ((this as ExtensionAware).extensions.getByName("kotlinOptions") as KotlinJvmOptions).apply {
        jvmTarget = "11"
    }
}