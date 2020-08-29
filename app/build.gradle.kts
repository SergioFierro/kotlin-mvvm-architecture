plugins {
  id(Config.Plugins.androidApplication)
  kotlin(Config.Plugins.kotlinAndroid)
  kotlin(Config.Plugins.kotlinAndroidExtensions)
  kotlin(Config.Plugins.kapt)
  id(Config.Plugins.daggerHiltAndroid)
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
    buildConfigField("String", "BASE_URL", "\"https://api.mercadolibre.com/\"")
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    dataBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  packagingOptions {
    exclude("META-INF/DEPENDENCIES")
    exclude("META-INF/LICENSE")
    exclude("META-INF/LICENSE.txt")
    exclude("META-INF/license.txt")
    exclude("META-INF/NOTICE")
    exclude("META-INF/NOTICE.txt")
    exclude("META-INF/notice.txt")
    exclude("META-INF/ASL2.0")
    exclude("META-INF/*.kotlin_module")
  }
  testOptions.unitTests.apply {
    isIncludeAndroidResources = true
    isReturnDefaultValues = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "includes" to listOf("*.jar"))))
  implementation(Config.Dependencies.kotlin)
  implementation(Config.Dependencies.androidCore)
  implementation(Config.Dependencies.appcompat)
  implementation(Config.Dependencies.constraintLayout)
  implementation(Config.Dependencies.recyclerview)

  // architecture components
  implementation(Config.Dependencies.lifecycleExtensions)
  implementation(Config.Dependencies.lifecycleLiveData)
  implementation(Config.Dependencies.lifecycleViewModel)
  implementation(Config.Dependencies.lifecycleSavedState)
  implementation(Config.Dependencies.fragment)

  // network
  implementation(Config.Dependencies.retrofit)
  implementation(Config.Dependencies.moshi)
  implementation(Config.Dependencies.moshiKotlin)
  implementation(Config.Dependencies.moshiCodegen)
  implementation(Config.Dependencies.sandwich)
  implementation(Config.Dependencies.okHttpLogging)

  // dagger
  implementation(Config.Dependencies.daggerHiltAndroid)
  implementation(Config.Dependencies.daggerHilt)
  implementation(Config.Dependencies.daggerHiltLifecycleViewModel)
  kapt(Config.Dependencies.daggerHiltAndroidKapt)
  kapt(Config.Dependencies.daggerHiltKapt)
  androidTestImplementation(Config.Dependencies.daggerHiltAndroidTesting)
  kaptAndroidTest(Config.Dependencies.daggerHiltAndroidKapt)

  // unit test
  testImplementation(Config.Dependencies.junit)
  testImplementation(Config.Dependencies.mockWebServer)
  testImplementation(Config.Dependencies.mockitoKotlin)
  testImplementation(Config.Dependencies.mockitoInline)
  testImplementation(Config.Dependencies.robolectric)
  testImplementation(Config.Dependencies.coreTesting)
  testImplementation(Config.Dependencies.core)
}
