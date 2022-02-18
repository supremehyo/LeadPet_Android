subprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        gradlePluginPortal()

    }
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}