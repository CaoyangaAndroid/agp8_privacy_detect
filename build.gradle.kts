// Top-level build file where you can add configuration options common to all sub-projects/modules.
// 1. 先添加JitPack仓库
buildscript {
    dependencies {
        // 使用JitPack生成的完整坐标
        classpath("com.github.CaoyangaAndroid.agp8_privacy_detect:privacy-plugin:41b7888fdf")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}