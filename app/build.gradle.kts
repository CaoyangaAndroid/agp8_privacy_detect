

plugins {
    alias(libs.plugins.android.application)
    id("com.caoyang.trace.privacy")
    alias(libs.plugins.jetbrains.kotlin.android)
}

extensions.configure<com.example.privacy_detect_plugins.plugins.privacy.PrivacySentryPluginParameter> {
    methodOwner = "com.example.privacydetect.privacy_detect.PrivacySentryRecord"
    methodName = "writeToFile"
}

android {
    namespace = "com.example.privacydetect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.privacydetect"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("commons-io:commons-io:2.15.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}