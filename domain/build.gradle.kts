plugins {
    id("com.android.library")
    id("common-config-plugin")
}

android {
    namespace = "${ApplicationId.id}.domain"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":common"))
}