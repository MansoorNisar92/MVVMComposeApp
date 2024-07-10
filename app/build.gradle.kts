plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("org.jetbrains.kotlin.kapt")
    id ("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.android.app.mvvmcomposeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.app.mvvmcomposeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.android.app.mvvmcomposeapp.utils.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation("com.google.dagger:hilt-android-testing:2.48.1")
    implementation("com.google.dagger:hilt-android-testing:2.48.1")
    implementation( "androidx.arch.core:core-testing:2.1.0")
    implementation("com.google.truth:truth:1.1.2")

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization.json)

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.1.1")
    testImplementation("org.mockito:mockito-inline:5.1.1")

    // Mockito for Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Coroutine testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0") // Ensure compatibility with your Kotlin version

}
