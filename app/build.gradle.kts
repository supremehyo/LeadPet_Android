plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}


android {
    signingConfigs {
        create("release") {
        }
    }
    compileSdk = Versions.COMPILE_SDK_VERSION
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.dev6.LeadPet"
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
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


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(":features:feed"))
    implementation(project(":features:login"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":features:join"))
    implementation(project(":features:post"))


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


    //retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.GSON)
    implementation(Network.SCALAR)


    implementation(Google.HILT_ANDROID)


    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    kapt(Google.HILT_ANDROID_COMPILER)

    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation(Google.MATERIAL)

    implementation(Google.GOOGLE_CORE)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.CRASHLYTICS)
    implementation(Google.ANALYTICS)
    implementation(Image.FIREBASE_STORAGE)

    testImplementation(UnitTest.JUNIT)
    //androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)



    //디버그용
    implementation(Debug.TIMBER)
    debugImplementation(Memory.LEAK_CANARY)

    implementation(Kakao.LOGIN)

    implementation(Room.ROOM)
    kapt(Room.ROOM_COMPILER)
    testImplementation(Room.ROOM_TESTING)

}