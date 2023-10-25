@file:Suppress("UnstableApiUsage")

import dagger.hilt.android.plugin.HiltExtension

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")

    id("com.google.dagger.hilt.android")
    kotlin("kapt")

    id("common-config-plugin")
}

the<HiltExtension>().enableAggregatingTask = false

android {
    namespace = "${ApplicationId.id}.playground"

    compileSdk = Versions.androidCompileSdkVersion
    buildToolsVersion = "30.0.3"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    defaultConfig {
        applicationId = "${ApplicationId.id}.application"

        minSdk = Versions.androidMinSdkVersion
        targetSdk = Versions.androidTargetSdkVersion

        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    sourceSets {
        named("main") {
            res.srcDirs(
                "src/main/res",
                "src/main/res/custom-res/splash"
            )
        }
        named("main") {
            java.srcDirs(
                "src/main/base/customWidgets/cameraView/base",
                "src/main/base/customWidgets/cameraView/api9",
                "src/main/base/customWidgets/cameraView/api14",
                "src/main/base/customWidgets/cameraView/api21",
                "src/main/base/customWidgets/cameraView/api23"
            )
        }
    }

    buildTypes {
        named("debug") {
            isDebuggable = true
            ext.set("enableCrashlytics", false)
        }

        named("release") {
            ndk {
                abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "armeabi"))
            }

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                android.getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
            ext.set("enableCrashlytics", true)
        }
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}")

    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}
