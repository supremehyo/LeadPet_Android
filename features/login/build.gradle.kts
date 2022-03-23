plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation (project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
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


    //retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.GSON)

    implementation(Google.MATERIAL)

    //로그인
    implementation(Google.AUTH)
    implementation("com.google.firebase:firebase-auth-ktx:21.0.2")
    implementation("com.google.android.gms:play-services-auth:20.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation(Kakao.LOGIN)

    implementation("androidx.appcompat:appcompat:1.4.0")

    implementation(Google.HILT_ANDROID)


    annotationProcessor(Google.HILT_COMPILER)
    kapt(Google.HILT_ANDROID_COMPILER)


    implementation(Google.GOOGLE_CORE)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.CRASHLYTICS)
    implementation(Google.ANALYTICS)


    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    implementation(AndroidX.NAVIGATION)
    implementation(AndroidX.NAVIGATION_KTX)

    //디버그용
    implementation(Debug.TIMBER)



}