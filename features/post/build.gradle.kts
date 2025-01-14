plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    implementation(project(":core"))
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

    implementation(Google.AUTH)
    implementation("com.google.android.gms:play-services-auth:19.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.github.skydoves:balloon:1.4.5")

    implementation("androidx.appcompat:appcompat:1.4.0")

    implementation(Google.HILT_ANDROID)
    implementation(AndroidX.CONSTRAINT_LAYOUT)
    kapt(Google.HILT_ANDROID_COMPILER)


    implementation(Google.GOOGLE_CORE)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.CRASHLYTICS)
    implementation(Google.ANALYTICS)


    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.TRUTH)
    testImplementation(UnitTest.MOCKITO)
    testImplementation(UnitTest.TURBINE)

    //androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(AndroidTest.ESPRESSO_CONTRIB)
    androidTestImplementation(AndroidTest.ANDROID_RUNNER)
    androidTestImplementation(Kotlin.COROUTINES_TEST)

    implementation(AndroidX.NAVIGATION)
    implementation(AndroidX.NAVIGATION_KTX)

    //디버그용
    implementation(Debug.TIMBER)
    implementation(Kakao.LOGIN)

    //Groupie
    implementation(RecyclerView.Groupie)
    implementation(RecyclerView.GroupieViewBinding)

    //Image
    implementation(Image.GLIDE)
    implementation(Image.GLIDE_COMPILER)
    implementation(Image.TED_IMAGE_PICKER)

    implementation(Indicator.CIRCLE_INDICATOR)

    //debugImplementation(Memory.LEAK_CANARY)
    testImplementation(Kotlin.COROUTINES_TEST)
}