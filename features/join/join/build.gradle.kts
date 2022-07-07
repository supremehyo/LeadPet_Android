plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

}

android {
    compileSdk = 31
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")


    //Image
    implementation(Image.GLIDE)
    implementation(Image.GLIDE_COMPILER)
    implementation(Image.TED_IMAGE_PICKER)


   // implementation(Google.HILT_ANDROID)
   // implementation(Google.HILT_LIFECYCLE)
   // kapt(Google.HILT_ANDROID_COMPILER)
    //hilt
    implementation(Google.HILT_ANDROID)
    androidTestImplementation("junit:junit:4.12")
    annotationProcessor(Google.HILT_COMPILER)
    kapt(Google.HILT_ANDROID_COMPILER)


    //Navigation
    implementation(AndroidX.NAVIGATION)
    implementation(AndroidX.NAVIGATION_KTX)

    implementation (project(":data"))
    implementation (project(":domain"))
    implementation (project(":core"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //디버그용
    implementation(Debug.TIMBER)

}