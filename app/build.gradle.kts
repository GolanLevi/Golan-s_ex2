plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.golans_ex2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.golans_ex2"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.media3.common)
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Gson
    implementation (libs.gson)
    implementation (libs.play.services.maps.v1802)
    implementation (libs.play.services.location.v2101)
    implementation (libs.play.services.maps.v1700)
    implementation(libs.play.services.maps)


}