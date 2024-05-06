plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.divao.pokedapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.divao.pokedapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.dagger:dagger:2.51.1")
    implementation("com.github.terrakok:cicerone:5.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")
    implementation("com.github.pakoito:RxPaper2:1.1.0")
    implementation("com.evernote:android-state:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.jakewharton.rxbinding2:rxbinding-kotlin:2.1.1")
    implementation("com.jakewharton.rxbinding2:rxbinding-design-kotlin:2.1.1")
    implementation("com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:2.1.1")
    implementation("com.jakewharton.rxbinding2:rxbinding-recyclerview-v7-kotlin:2.1.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.github.lisawray.groupie:groupie:2.9.0")
    implementation("com.xwray:groupie-kotlin-android-extensions:2.9.0")
    implementation("com.github.lisawray.groupie:groupie-databinding:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.9.0")
    implementation("jp.wasabeef:glide-transformations:4.0.0")
    kapt("com.google.dagger:dagger-android-processor:2.51.1")
    kapt("com.google.dagger:dagger-compiler:2.51.1")
    implementation(project(":domain"))
    annotationProcessor("com.google.dagger:dagger-compiler:2.51.1")
    annotationProcessor("com.evernote:android-state-processor:1.3.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}