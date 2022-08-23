plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
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
    implementation (project(":data"))
    implementation (project(":domain"))
    implementation (project(":core"))
    implementation (project(":features:feed"))

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
    implementation(AndroidX.CONSTRAINT_LAYOUT)

    //Image
    implementation(Image.GLIDE)
    implementation(Image.GLIDE_COMPILER)
    implementation(Image.TED_IMAGE_PICKER)

    //hilt
    implementation(Google.HILT_ANDROID)
    implementation(Google.MATERIAL)
    androidTestImplementation("junit:junit:4.12")
    kapt(Google.HILT_ANDROID_COMPILER)


    //Navigation
    implementation(AndroidX.NAVIGATION)
    implementation(AndroidX.NAVIGATION_KTX)


    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //디버그용
    implementation(Debug.TIMBER)

}