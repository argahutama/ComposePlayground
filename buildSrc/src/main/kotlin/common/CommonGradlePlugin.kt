package common

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureKapt()
        target.configureAndroid()
        target.commonDependenciesSetup()
    }

    private fun Project.configurePlugins() {
        plugins.apply("kotlin-android")
        plugins.apply("kotlin-parcelize")
        plugins.apply("com.google.dagger.hilt.android")
        plugins.apply("kotlin-kapt")
    }

    private fun Project.configureKapt() {
        extensions.getByType(KaptExtension::class.java).run {
            this.correctErrorTypes = true
        }
    }

    private fun Project.configureAndroid() {
        extensions.getByType(BaseExtension::class.java).run {
            namespace = ApplicationId.id

            compileSdkVersion(Versions.androidCompileSdkVersion)

            defaultConfig {
                minSdk = Versions.androidMinSdkVersion
                targetSdk = Versions.androidTargetSdkVersion

                versionCode = Versions.versionCode
                versionName = Versions.versionName
            }

            tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }

            buildFeatures.run {
                viewBinding {
                    enable = true
                }
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.3"
            }

            compileOptions {
                sourceCompatibility(JavaVersion.VERSION_11)
                targetCompatibility(JavaVersion.VERSION_11)
            }

            flavorDimensions("env")
            productFlavors {
                create("staging") {
                    buildConfigField("boolean", "PRODUCTION", "false")
                }
            }
        }
    }
}
