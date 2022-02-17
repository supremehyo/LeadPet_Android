plugins {
    `kotlin-dsl`
}


repositories {
    google()
    mavenCentral()
    maven ( "https://jitpack.io" )
    gradlePluginPortal()

}
dependencies {
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersion.GRADLE_PLUGIN}")
    implementation( "com.google.gms:google-services:4.3.10")
    implementation( "com.google.firebase:firebase-crashlytics-gradle:2.8.1")

}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}