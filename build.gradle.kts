buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.apptimize.com/artifactory/repo") }
        maven {
            url = uri("https://raw.githubusercontent.com/acoustic-analytics/Android_Maven/master")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
