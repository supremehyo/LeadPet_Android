plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(mapOf("path" to ":core")))

    implementation(Kakao.LOGIN)
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.SCALAR)
    implementation(Network.GSON)
    implementation(Network.MOCKWEBSERVER)
    implementation(AndroidX.PAGING)
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation(Debug.TIMBER)
    implementation(Google.AUTH)
    implementation(platform(Google.FIREBASE_BOM))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation(Kotlin.COROUTINES_TEST)
    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.TRUTH)
    testImplementation(Google.HILT_TESTING)
    testImplementation(AndroidX.CORE_TESTING)

    //androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Kotlin.COROUTINES_TEST)

    implementation(Room.ROOM)
    kapt(Room.ROOM_COMPILER)
    testImplementation(Room.ROOM_TESTING)
    implementation("com.google.firebase:firebase-storage-ktx:20.0.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.2.2")

}