plugins {
    id("com.android.library")
    id("common-config-plugin")
}

android {
    namespace = "${ApplicationId.id}.convert"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
}