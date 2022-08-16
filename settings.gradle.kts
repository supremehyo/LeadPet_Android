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
        maven ( "https://jitpack.io" )
        maven ( url= "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}
rootProject.name = "LeadPet"
include (":app")
include (":core")
include (":data")
include (":domain")
include(":features:login")
include(":features:join")


include(":features:feed")
include(":features:post")

