plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

kotlin { explicitApi() }

publishing { publications { create<MavenPublication>("domain") { from(components["java"]) } } }

dependencies { implementation(libs.coroutinesCore) }
