plugins {
    id("com.android.library")
    id("common-config-plugin")
}

android {
    namespace = "${ApplicationId.id}.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {}
