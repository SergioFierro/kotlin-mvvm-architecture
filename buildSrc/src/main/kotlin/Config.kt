object Config {
  object Dependencies {
    const val androidTools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val junit = "junit:junit:${Versions.junit}"

    // architecture components
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"

    // dagger
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroid}"
    const val daggerHiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.daggerHiltAndroid}"
    const val daggerHilt = "androidx.hilt:hilt-common:${Versions.daggerHilt}"
    const val daggerHiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHilt}"
    const val daggerHiltAndroidKapt = "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroid}"
    const val daggerHiltKapt = "androidx.hilt:hilt-compiler:${Versions.daggerHilt}"

    // unit test
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val core = "androidx.test:core:${Versions.core}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
  }

  object Plugins {
    const val kotlin = "gradle-plugin"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlinAndroidExtensions = "android.extensions"
    const val daggerHiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltAndroid}"
    const val daggerHiltAndroid = "dagger.hilt.android.plugin"
    const val kapt = "kapt"
    const val spotless = "plugins.spotless"
    const val ktlint = "plugins.ktlint"
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
    const val recyclerview = "1.1.0"
    const val junit = "4.12"
    const val retrofit = "2.9.0"
    const val moshi = "1.9.2"
    const val okHttp = "4.7.2"
    const val sandwich = "1.0.4"
    const val daggerHiltAndroid = "2.28.3-alpha"
    const val daggerHilt = "1.0.0-alpha02"
    const val lifecycle = "2.2.0"
    const val fragment = "1.2.5"
    const val robolectric = "4.3.1"
    const val mockitoKotlin = "2.2.0"
    const val mockitoInline = "3.3.3"
    const val coreTesting = "2.1.0"
    const val core = "1.3.0"
    const val turbine = "0.2.0"
  }
}
