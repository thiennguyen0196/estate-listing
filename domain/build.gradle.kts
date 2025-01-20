plugins {
    alias(libs.plugins.java.library)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlinx.kover)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    // Coroutines
    implementation(libs.coroutines.core)

    // Javax Inject
    implementation(libs.javax.inject)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest)
}