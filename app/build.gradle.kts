plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    kotlin("android")
    kotlin("android.extensions")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"
    flavorDimensions("default")

    defaultConfig {
        applicationId("app.test.offline")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "ARCHIVE_BASE_NAME", "\"Test app offline watcher\"")
            buildConfigField("String", "BASE_URL", "\"\"")
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "$project.rootDir/tools/proguard-rules-debug.pro"
            )
        }

        named("release") {
            buildConfigField("String", "ARCHIVE_BASE_NAME", "\"Test app offline watcher\"")
            buildConfigField("String", "BASE_URL", "\"\"")
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "$project.rootDir/tools/proguard-rules.pro"
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.3")

    /**
     * lifecycle
     */
    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.3.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0")

    /**
     *  Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    /**
     * Hilt
     */
    implementation("com.google.dagger:hilt-android:2.31-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.31-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    /**
     * Room
     */
    api("androidx.room:room-ktx:2.2.6")
    api("androidx.room:room-runtime:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")


    /**
     *  ExoPlayer
     */
    implementation("com.google.android.exoplayer:exoplayer:2.12.1")
    implementation("com.google.android.exoplayer:exoplayer-core:2.12.1")
    implementation("com.google.android.exoplayer:exoplayer-dash:2.12.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.12.1")

    /**
     * Gson
     * */
    implementation("com.google.code.gson:gson:2.8.6")

    /**
     *  GLIDE
     */
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    //PRDownloader - https://github.com/MindorksOpenSource/PRDownloader
    implementation("com.mindorks.android:prdownloader:0.6.0")


    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}