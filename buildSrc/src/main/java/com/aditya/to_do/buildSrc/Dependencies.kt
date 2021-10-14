package com.aditya.to_do.buildSrc

object Libs{

    object Hilt {
        private const val hiltVersion = "2.37"
        private const val hiltCompilerVersion = "1.0.0"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val android = "com.google.dagger:hilt-android:$hiltVersion"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val compiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
    }

    object DataStore {
        private const val dataStoreVersion = "1.0.0"
        const val datastorePref = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    }

    object Room {
        private const val roomVersion = "2.4.0-alpha05"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }

    object Navigation {
        private const val navComposeVer = "2.4.0-alpha06"
        const val navigation = "androidx.navigation:navigation-compose:$navComposeVer"
    }

    object Gradle {
        private const val agpVersion = "7.0.2"
        const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"
    }

    object Kotlin {
        private const val version = "1.5.30"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object AndroidX {
        private const val appCompatVer = "1.3.1"
        private const val coreKtxVer = "1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:$appCompatVer"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVer"
    }

    object Material {
        private const val materialVersion = "1.4.0"
        const val material = "com.google.android.material:material:$materialVersion"
    }

    object LifeCycle {
        private const val lifeCycleVer = "2.3.1"
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVer"
    }

    object JUnit {
        private const val extJunitVer = "1.1.3"
        private const val junitVer = "4.13.2"
        const val extJUnit = "androidx.test.ext:junit:$extJunitVer"
        const val jUnit = "junit:junit:$junitVer"
    }

    object Espresso {
        private const val espressoVer = "3.4.0"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVer"
    }

    object Compose {
        private const val composeVer = "1.0.3"
        private const val activityComposeVer = "1.3.1"
        const val ui = "androidx.compose.ui:ui:$composeVer"
        const val material = "androidx.compose.material:material:$composeVer"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVer"
        const val toolingPrev = "androidx.compose.ui:ui-tooling-preview:$composeVer"
        const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:$composeVer"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVer"
    }
}