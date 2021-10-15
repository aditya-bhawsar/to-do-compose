package com.aditya.to_do.buildSrc

object Version{
    const val hiltVersion = "2.37"
    const val hiltCompilerVersion = "1.0.0"
    const val dataStoreVersion = "1.0.0"
    const val roomVersion = "2.4.0-alpha05"
    const val navComposeVer = "2.4.0-alpha06"
    const val agpVersion = "7.0.2"
    const val appCompatVer = "1.3.1"
    const val coreKtxVer = "1.6.0"
    const val kotlinVersion = "1.5.30"
    const val materialVersion = "1.4.0"
    const val lifeCycleVer = "2.3.1"
    const val extJunitVer = "1.1.3"
    const val junitVer = "4.13.2"
    const val espressoVer = "3.4.0"
    const val composeVer = "1.0.3"
    const val activityComposeVer = "1.3.1"
    const val ktLintVer = "10.1.0"
}

object Libs{

    object Hilt {
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.hiltVersion}"
        const val android = "com.google.dagger:hilt-android:${Version.hiltVersion}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Version.hiltVersion}"
        const val compiler = "androidx.hilt:hilt-compiler:${Version.hiltCompilerVersion}"
    }

    object DataStore {
        const val datastorePref = "androidx.datastore:datastore-preferences:${Version.dataStoreVersion}"
    }

    object Room {
        const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
        const val roomRuntime = "androidx.room:room-runtime:${Version.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"
    }

    object Navigation {
        const val navigation = "androidx.navigation:navigation-compose:${Version.navComposeVer}"
    }

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.agpVersion}"
    }

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Version.appCompatVer}"
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtxVer}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Version.materialVersion}"
    }

    object LifeCycle {
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycleVer}"
    }

    object KtLint {
        const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktLintVer}"
    }

    object JUnit {
        const val extJUnit = "androidx.test.ext:junit:${Version.extJunitVer}"
        const val jUnit = "junit:junit:${Version.junitVer}"
    }

    object Espresso {
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoVer}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Version.composeVer}"
        const val material = "androidx.compose.material:material:${Version.composeVer}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Version.composeVer}"
        const val toolingPrev = "androidx.compose.ui:ui-tooling-preview:${Version.composeVer}"
        const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Version.composeVer}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityComposeVer}"
    }
}