plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")

}


android {
    compileSdkVersion(Versions.COMPILE_SDK_VERSION)
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = "com.dev6.LeadPet"
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)
        vectorDrawables.useSupportLibrary = true
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                    getDefaultProguardFile(
                            "proguard-android-optimize.txt"
                    ),
                    "proguard-rules.pro"
            )
        }
    }
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation (Kotlin.KOTLIN_STDLIB)
    implementation (Kotlin.COROUTINES_ANDROID)
    implementation (Kotlin.COROUTINES_CORE)

    implementation (AndroidX.CORE_KTX)
    implementation (AndroidX.APP_COMPAT)
    implementation (AndroidX.ACTIVITY_KTX)
    implementation (AndroidX.FRAGMENT_KTX)
    implementation (AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation (AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation (AndroidX.LIFECYCLE_EXTENSIONNS)


    //retrofit2
    implementation (Libraries.OKHTTP)
    implementation (Libraries.OKHTTP_LOGGING_INTERCEPTOR)
    implementation (Libraries.RETROFIT)
    implementation (Libraries.RETROFIT_CONVERTER_GSON)

    implementation (Google.MATERIAL)
    implementation (Google.HILT_ANDROID)
    // implementation (Google.HILT_ANDROID_COMPILER)
    kapt ("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    implementation("androidx.appcompat:appcompat:1.4.0")
    testImplementation (UnitTest.JUNIT)

    androidTestImplementation (AndroidTest.ANDROID_JUNIT)
    androidTestImplementation (AndroidTest.ESPRESSO_CORE)

    //멀티모듈 관리
    implementation(project(":api"))
    implementation(project(":repository"))
}