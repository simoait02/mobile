plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mvvnd"
    compileSdk = 35

    // Enable ViewBinding
    viewBinding {
        enable = true
    }

    defaultConfig {
        applicationId = "com.example.mvvnd"
        minSdk = 34
        targetSdk = 34
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
}

dependencies {
    // Appcompat and Material design libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // LiveData and ViewModel dependencies for MVVM architecture
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")  // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")    // LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")     // Lifecycle components

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // DataBinding runtime (if you decide to use DataBinding)
    // If you want to use ViewBinding, you don’t need the DataBinding dependencies
    implementation("androidx.databinding:databinding-runtime:7.3.0")

    // Optional: You can remove this if not using lifecycle-extensions anymore
    // implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.2.0")
}
