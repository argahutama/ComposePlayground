package common

import AndroidLibraries
import DataLibraries
import DependencyInjectionLibraries
import PresentationLibraries
import PresentationTestLibraries
import UnitTestLibraries
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies
import utils.*

internal fun Project.commonDependenciesSetup() {
    dependencies {

        includeAndroidDependencies()
        includePresentationDependencies()
        includeDataDependencies()
        includeTestDependencies()

        implementation(DependencyInjectionLibraries.hilt)
        implementation(DependencyInjectionLibraries.hiltWork)
        implementation(DependencyInjectionLibraries.workRuntime)
        implementation(DependencyInjectionLibraries.hiltNavigationCompose)

        add("kapt", DependencyInjectionLibraries.hiltAndroidCompiler)
        add("kapt", DependencyInjectionLibraries.hiltCompiler)
    }
}

private fun DependencyHandler.includeAndroidDependencies() {
    implementation(AndroidLibraries.androidCore)
    implementation(AndroidLibraries.multidex)
}

private fun DependencyHandler.includePresentationDependencies() {
    implementation(PresentationLibraries.lifecycleRuntime)
    implementation(PresentationLibraries.activityCompose)
    implementation(platform(PresentationLibraries.composeBom))
    implementation(PresentationLibraries.composeUi)
    implementation(PresentationLibraries.composeUiGraphics)
    implementation(PresentationLibraries.composeUiToolingPreview)
    implementation(PresentationLibraries.composeUiMaterial)
    implementation(PresentationLibraries.composeRuntime)
    implementation(PresentationLibraries.coilImage)
    implementation(PresentationLibraries.composeShimmer)
    debugImplementation(PresentationLibraries.composeTooling)
    debugImplementation(PresentationLibraries.composeTestManifest)
}

private fun DependencyHandler.includeDataDependencies() {
    implementation(DataLibraries.okHttp)
    implementation(DataLibraries.okHttpLogginInterceptor)
    implementation(DataLibraries.retrofit)
    implementation(DataLibraries.retrofitGsonConverter)
    implementation(DataLibraries.gson)
}

private fun DependencyHandler.includeTestDependencies() {
    testImplementation(UnitTestLibraries.jUnit)
    testImplementation(UnitTestLibraries.mockitoCore)
    testImplementation(UnitTestLibraries.mockitoKotlin)
    testImplementation(UnitTestLibraries.mockK)
    testImplementation(UnitTestLibraries.coroutinesTest)
    testImplementation(UnitTestLibraries.coreTesting)
    testImplementation(UnitTestLibraries.robolectric)

    androidTestImplementation(PresentationTestLibraries.jUnitExt)
    androidTestImplementation(PresentationTestLibraries.espressoCore)
    androidTestImplementation(PresentationTestLibraries.testRunner)
    androidTestImplementation(platform(PresentationLibraries.composeBom))
}