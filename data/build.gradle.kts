plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlinx.kover)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.thiennguyen.estatelisting.data"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    lint {
        checkDependencies = true
        xmlReport = true
        xmlOutput = file("build/reports/lint/lint-result.xml")
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))

    // Android
    implementation(libs.androidx.core.ktx)

    // Javax Inject
    implementation(libs.javax.inject)

    // Retrofit
    api(libs.retrofit)
    api(libs.retrofit.converter.moshi)

    // OkHttp
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)

    // Moshi
    api(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.kotlin)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest)
    testImplementation(libs.turbine)
}