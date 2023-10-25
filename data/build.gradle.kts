plugins {
    id("com.android.library")
    id("common-config-plugin")
}

android {
    namespace = "${ApplicationId.id}.data"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":common"))
}