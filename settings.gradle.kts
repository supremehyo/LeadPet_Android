pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven ( "https://jitpack.io" )
    }
}
rootProject.name = "LeadPet"
include (":app")
include (":core")
include (":data")
include (":domain")

include(":features:login")
