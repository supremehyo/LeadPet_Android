plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven ( "https://jitpack.io" )
    gradlePluginPortal()
}

object PluginVersion {
    const val GRADLE = "7.1.0-beta05"
    const val KOTLIN = "1.6.10"
    const val GRADLE_PLUGIN = "0.41.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersion.GRADLE_PLUGIN}")
    implementation( "com.google.gms:google-services:4.3.10")
    implementation( "com.google.firebase:firebase-crashlytics-gradle:2.8.1")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.2")

}