import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.ktx
    id("com.codingfeline.buildkonfig")
}

version = App.version

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Common.coroutines)
                implementation(Dependencies.Common.kodein)
                implementation(Dependencies.Common.ktor)
                implementation(Dependencies.Common.ktorSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Common.testCommonAnnotations))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Android.sqlDelight)
                implementation(Dependencies.Android.ktor)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.IOS.sqlDelight)
                implementation(Dependencies.IOS.ktor)
            }
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }

    /**
     * This makes compile the application for iOs.
     *
     * Theres is needed add the -lsqlite3
     *
     * [Issue link][https://github.com/JetBrains/kotlin-native/issues/3672]
     *
     * Other way to do this:
     *
     * 1. In the cocoapods.framework add: isStatic = true
     * 2. Open shared.podspec
     * 3. In spec.libraries add "sqlite3".
     */
    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().forEach { target ->
        target.binaries.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
            .forEach { lib ->
                lib.isStatic = false
                lib.linkerOpts.add("-lsqlite3")
            }
    }
}

android {
    compileSdk = App.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = App.minSdk
        targetSdk = App.targetSdk
    }
}

// Documentation: https://cashapp.github.io/sqldelight/multiplatform_sqlite/gradle/
sqldelight {
    database("OKMoviesPlaceDatabase") {
        packageName = "${App.packageRoot}db"
    }
}

buildkonfig {
    packageName = App.packageRoot.substring(0, App.packageRoot.length - 1)

    val localPropertiesFile = "local.properties"
    val theMovieApiKey = "theMovieDBApiKey"

    defaultConfigs {
        buildConfigField(Type.STRING, theMovieApiKey, project.readProperty(theMovieApiKey, localPropertiesFile))
    }
}
