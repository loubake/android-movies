import java.util.Properties

val keysProperties = Properties()
val keysPropertiesFileName = "keys.properties"
keysProperties.load(project.rootProject.file(keysPropertiesFileName).inputStream())
val theMovieDbApiKeyValue: String = keysProperties.getProperty("TheMovieDbApiKey")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.loubake.androidmovies"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.loubake.androidmovies"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildTypes.forEach {
        val theMoveDbApiKeyName = "THE_MOVIE_DB_API_KEY"
        it.buildConfigField("String", theMoveDbApiKeyName, theMovieDbApiKeyValue)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Kotlin adn Android Dependencies Versions:
val kotlin_version: String = "1.3.50"
val android_core_kts_version: String = "1.9.0"
val androidx_appcompat_version: String = "1.6.1"
val androidx_lifecycle_version: String = "2.0.0"

// Test Dependencies Versions:
val junit_version: String = "4.13.2"
val androidx_junit_version: String = "1.1.5"
val androidx_expresso_version: String = "3.5.1"

// Kotlin Coroutines Dependencies Versions:
val kotlin_coroutines_version: String = "1.3.0"

// Layout Dependencies Versions:
val constraint_layout_version: String = "1.1.3"
val material_version: String = "1.9.0"
val androidx_recyclerview_version: String = "1.1.0"
val androidx_cardview_version: String = "1.0.0"

// Glide Dependencies Versions:
val glide_version: String = "4.11.0"

// Koin Dependencies Versions:
val koin_version: String = "3.4.3"

// Retrofit Dependencies Versions:
val retrofit_version: String = "2.9.0"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("androidx.core:core-ktx:$android_core_kts_version")
    implementation("androidx.appcompat:appcompat:$androidx_appcompat_version")
    implementation("androidx.lifecycle:lifecycle-extensions:$androidx_lifecycle_version")

    // Layout
    implementation("androidx.constraintlayout:constraintlayout:$constraint_layout_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.recyclerview:recyclerview:$androidx_recyclerview_version")
    implementation("androidx.cardview:cardview:$androidx_cardview_version")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide_version")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines_version")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Koin
    implementation("io.insert-koin:koin-android:$koin_version")
    implementation("io.insert-koin:koin-android-compat:$koin_version")
    implementation("io.insert-koin:koin-androidx-workmanager:$koin_version")
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")

    // Test
    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$androidx_junit_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$androidx_expresso_version")
}
