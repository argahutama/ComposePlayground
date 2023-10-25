object ApplicationId {
    const val id = "com.argahutama.compose"
}

object Versions {
    const val versionMajor = 1
    const val versionMedium = 0
    const val versionMinor = 0
    const val buildNum = 1

    val versionCode
        get() = 100000000 + (
            100 * Versions.versionMajor + 10 * Versions.versionMedium + Versions.versionMinor
            ) * 1000 + Versions.buildNum

    val versionName
        get() = "${Versions.versionMajor}.${Versions.versionMedium}.${Versions.versionMinor}"

    const val androidMinSdkVersion = 24
    const val androidTargetSdkVersion = 33
    const val androidCompileSdkVersion = 33

    const val kotlin = "1.8.10"
    const val retrofit = "2.9.0"
    const val hilt = "2.47"
    const val okHttp = "4.9.1"
}

object AndroidLibraries {
    const val androidCore = "androidx.core:core-ktx:1.10.1"
    const val multidex = "com.android.support:multidex:1.0.3"
}

object UnitTestLibraries {
    const val jUnit = "junit:junit:4.13.2"
    const val mockitoCore = "org.mockito:mockito-core:5.4.0"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:5.0.0"
    const val mockK = "io.mockk:mockk:1.13.5"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    const val coreTesting = "androidx.arch.core:core-testing:2.2.0"
    const val robolectric = "org.robolectric:robolectric:4.9"
}

object PresentationTestLibraries {
    const val jUnitExt = "androidx.test.ext:junit:1.1.5"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    const val testRunner = "androidx.test:runner:1.3.0"
}

object PresentationLibraries {
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    const val activityCompose = "androidx.activity:activity-compose:1.7.2"
    const val composeBom = "androidx.compose:compose-bom:2023.03.00"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiMaterial = "androidx.compose.material3:material3"
    const val composeTooling = "androidx.compose.ui:ui-tooling"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest"
}

object DataLibraries {
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogginInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:2.8.9"
}

object DependencyInjectionLibraries {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}