object Versions {
    const val ktx = "1.6.0"
    const val gradle = "7.0.4"
    const val sqlDelight = "1.5.3"

    object Common {
        const val kodein = "7.10.0"
        const val ktor = "1.6.7"
    }

    object Android {
        const val material = "1.4.0"
        const val appCompat = "1.4.0"
        const val constraintLayout = "2.1.2"
        const val lifecycle = "2.4.0"
    }

}

object Dependencies {
    const val ktxGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ktx}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val sqlDelightGradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val sqlPlugin = "com.squareup.sqldelight"

    object Common {
        const val testCommonAnnotations = "test-annotations-common"
        const val kodein = "org.kodein.di:kodein-di:${Versions.Common.kodein}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.ktx}"
        const val ktor = "io.ktor:ktor-client-core:${Versions.Common.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.Common.ktor}"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.ktx}"
        const val sqlDelight = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val kodein = "org.kodein.di:kodein-di-framework-android-x-viewmodel:${Versions.Common.kodein}"
        const val ktor = "io.ktor:ktor-client-android:${Versions.Common.ktor}"
    }

    object IOS {
        const val sqlDelight = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val ktor = "io.ktor:ktor-client-ios:${Versions.Common.ktor}"
    }
}