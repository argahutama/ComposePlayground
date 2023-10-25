plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

gradlePlugin {
    plugins {
        register("common-config-plugin") {
            id = "common-config-plugin"
            implementationClass = "common.CommonGradlePlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
}