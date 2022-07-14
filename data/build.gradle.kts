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

    testImplementation(Kotlin.COROUTINES_TEST)

    //retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.SCALAR)
    implementation(Network.GSON)
    implementation(Network.MOCKWEBSERVER)


    implementation(AndroidX.PAGING)
    implementation(Google.HILT_ANDROID)
    annotationProcessor(Google.HILT_COMPILER)
    testImplementation(Google.HILT_TESTING)
    testImplementation(AndroidX.CORE_TESTING)


    implementation(project(mapOf("path" to ":core")))
    kapt(Google.HILT_ANDROID_COMPILER)

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    testImplementation(UnitTest.TRUTH)

    //디버그용
    implementation(Debug.TIMBER)

    implementation(Google.AUTH)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Kakao.LOGIN)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

}