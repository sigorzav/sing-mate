plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.sigorzav.singmate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sigorzav.singmate"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true  // Jetpack Compose 활성화
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.metadata.jvm)

    // Firebase
    // implementation(platform(libs.firebaseBom))

    // Jetpack Compose BOM (버전 자동 관리)
    implementation(platform(libs.compose.bom))

    // 기본 Compose UI
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)

    // Navigation (Compose 전용)
    implementation(libs.navigation.compose)

    // ViewModel + Compose 연동
    implementation(libs.lifecycle.viewmodel.compose)
    // implementation(libs.firebase.auth.ktx)

    // Debug
    debugImplementation(libs.debug.compose.ui.tooling)

    // Stetho
    implementation(libs.stetho)
    implementation(libs.stetho.okhttp3)
    implementation(libs.stetho.js.rhino)

    // API
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.core)

    // Jetpack Compose에서 'hiltViewModel()' 사용을 위해 추가
    implementation(libs.hilt.navigation.compose)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}