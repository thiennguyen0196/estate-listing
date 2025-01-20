import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.jetbrains.kotlinx.kover)
}

val signingProperties = loadProperties("$rootDir/signing.properties")
val envProperties = loadProperties("$rootDir/env.properties")
val baseApiUrl = envProperties.getProperty("BASE_API_URL") as String
val getVersionCode: () -> Int = {
    if (project.hasProperty("versionCode")) {
        (project.property("versionCode") as String).toInt()
    } else {
        Versions.ANDROID_VERSION_CODE
    }
}

android {
    namespace = "com.thiennguyen.estatelisting"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.thiennguyen.estatelisting"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = getVersionCode()
        versionName = libs.versions.androidVersionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create(BuildTypes.RELEASE) {
            // Remember to edit signing.properties to have the correct info for release build.
            storeFile = file("../config/release.keystore")
            storePassword = signingProperties.getProperty("KEYSTORE_PASSWORD") as String
            keyPassword = signingProperties.getProperty("KEY_PASSWORD") as String
            keyAlias = signingProperties.getProperty("KEY_ALIAS") as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs[BuildTypes.RELEASE]
        }

        debug {
            // For quickly testing build with proguard, enable this
            isMinifyEnabled = false
            signingConfig = signingConfigs[BuildTypes.DEBUG]
        }
    }

    flavorDimensions += Flavors.DIMENSION_VERSION
    productFlavors {
        create(Flavors.STAGING) {
            applicationIdSuffix = ".staging"
            buildConfigField("String", "BASE_API_URL", baseApiUrl)
        }

        create(Flavors.PRODUCTION) {
            buildConfigField("String", "BASE_API_URL", baseApiUrl)
        }
    }

    sourceSets["test"].resources {
        srcDir("src/test/resources")
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        checkDependencies = true
        xmlReport = true
        xmlOutput = file("build/reports/lint/lint-result.xml")
    }

    testOptions {
        unitTests {
            // Robolectric resource processing/loading https://github.com/robolectric/robolectric/pull/4736
            isIncludeAndroidResources = true
        }
        // Disable device's animation for instrument testing
        // animationsDisabled = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))

    // Android
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Image Loading
    implementation(libs.coil)

    // Google
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    // Timber
    implementation(libs.timber)

    // Chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest)
    testImplementation(libs.turbine)
}

// Kover configs
dependencies {
    kover(project(Modules.DATA))
    kover(project(Modules.DOMAIN))
}

koverReport {
    defaults {
        mergeWith("stagingDebug")
        filters {
            includes {
                classes("**.viewmodel.**", "**.usecase.**", "**.repository.**")
            }
            excludes {
                classes("**_**")
            }
        }
    }
}
