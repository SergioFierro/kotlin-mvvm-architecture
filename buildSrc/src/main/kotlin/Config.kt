/*
 * Copyright (c) 2020, Twilio Inc.
 */

object Config {
  object Dependencies {
    const val androidTools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
  }

  object Plugins {
    const val kotlin = "gradle-plugin"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
  }

  object Versions {
    const val kotlin = "1.3.72"
    const val gradle = "4.0.1"
    const val compileSDK = 30
    const val minSDK = 23
    const val targetSDK = 30
    const val buildTools = "29.0.3"
    const val androidCore = "1.3.1"
    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.0"
    const val junit = "4.12"
  }
}
