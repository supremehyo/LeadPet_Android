plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    //Android Core
    implementation(Kotlin.KOTLIN_STDLIB)
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.ACTIVITY_KTX)
    implementation(AndroidX.FRAGMENT_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(AndroidX.LIFECYCLE_EXTENSIONNS)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    implementation(Debug.TIMBER)

//    testImplementation(AndroidX.CORE_TESTING)
//    testImplementation(UnitTest.JUNIT)
//
//    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
//    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    //디버그용

}