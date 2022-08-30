plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.TRUTH)
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    testImplementation(Google.HILT_TESTING)
    implementation(Google.HILT_WORKER)
    implementation(AndroidX.WORK_MANAGER)
    androidTestImplementation(AndroidX.WORK_MANAGER)

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
