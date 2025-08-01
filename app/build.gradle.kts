plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.brainscript"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.brainscript"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    dependencies {
        val room_version = "2.7.1"
        val nav_version = "2.9.0"

        implementation("androidx.navigation:navigation-compose:$nav_version")
        implementation("androidx.room:room-runtime:$room_version")
        implementation("androidx.navigation:navigation-ui:$nav_version")

        implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
        androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        ksp("androidx.room:room-compiler:$room_version")  // keep this for Room KSP

        // Remove this line: annotationProcessor("androidx.room:room-compiler:$room_version")
        // instead use KSP above

        implementation("androidx.room:room-ktx:$room_version")
        implementation("androidx.compose.runtime:runtime-livedata:1.8.2")
        implementation("com.google.dagger:hilt-android:2.56.1")
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

        kapt("com.google.dagger:hilt-android-compiler:2.56.1")  // <-- kapt for Hilt

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
    }

}