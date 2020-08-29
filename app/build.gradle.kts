plugins {
  id(Config.Plugins.androidApplication)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinAndroidExtensions)
}

android {
  compileSdkVersion(Config.Versions.compileSDK)
  buildToolsVersion(Config.Versions.buildTools)

  defaultConfig {
    applicationId = "com.sergiofierro.meli"
    minSdkVersion(Config.Versions.minSDK)
    targetSdkVersion(Config.Versions.targetSDK)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "includes" to listOf("*.jar"))))
  implementation(Config.Dependencies.kotlin)
  implementation(Config.Dependencies.androidCore)
  implementation(Config.Dependencies.appcompat)
  implementation(Config.Dependencies.constraintLayout)
  testImplementation(Config.Dependencies.junit)
}