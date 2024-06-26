import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.nixfit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nixfit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Loads value from apiKeys.properties file
        val apiKeysFile = project.rootProject.file("apiKeys.properties")
        val properties = Properties()
        properties.load(apiKeysFile.inputStream())
        /* EXAMPLE FORMAT
        val nyTimesApiKey = properties.getProperty("NYTIMES_API_KEY") ?: ""
        buildConfigField(
            type = "String",
            name = "NYTIMES_API_KEY",
            value = nyTimesApiKey
        )
        */
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.play.services.mlkit.barcode.scanning)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Coil
    implementation(libs.coil.compose)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    // Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    // Arrow
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    // Google ML barcode scanner
    implementation(libs.play.services.code.scanner)
    // Data store
    implementation(libs.androidx.datastore.preferences)
    // Compose foundation
    implementation(libs.androidx.foundation)
    // Accompanist
    implementation(libs.accompanist.systemuicontroller)
    // Paging 3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    // Glide
    implementation(libs.glide)
    // Glide integration // Skip this if you don't want to use integration libraries or configure Glide.
    annotationProcessor(libs.compiler)
    // Splash
    implementation(libs.androidx.core.splashscreen)
    // Compose navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // Material3
    implementation(libs.material3)
}