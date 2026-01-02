import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = group.toString()
    compileSdk = 34
    defaultConfig { minSdk = 21 }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin { compilerOptions { jvmTarget = JvmTarget.JVM_17 } }
    publishing { singleVariant("release") { withSourcesJar() } }
}

kotlin { explicitApi() }

afterEvaluate {
    publishing { publications { create<MavenPublication>("core") { from(components["release"]) } } }
}

dependencies {
    api(project(":domain"))
    implementation(libs.preferencesDataStore)
}
