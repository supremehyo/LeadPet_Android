plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core"))

    // retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.GSON)
    testImplementation(UnitTest.MOCKITO)
    testImplementation(UnitTest.TRUTH)
    testImplementation(UnitTest.TURBINE)

    implementation(AndroidX.PAGING_DOMAIN)
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    testImplementation("junit:junit:4.13.2")

    // 디버그용
    implementation(Debug.TIMBER)

    implementation(Room.ROOM)
    kapt(Room.ROOM_COMPILER)
    testImplementation(Room.ROOM_TESTING)
}
