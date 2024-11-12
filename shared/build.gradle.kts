import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.inject.runtime)
                implementation(libs.kotlin.inject.anvil)
            }
        }

        jvmMain {
            dependsOn(commonMain)
        }

        androidMain {
            dependsOn(commonMain)
        }
    }
}

dependencies {
    configurations.forEach {
        if (it.name.startsWith("ksp") && it.name != "ksp") {
            println(it.name)
            add(it.name, libs.kotlin.inject.compiler.ksp)
            add(it.name, libs.kotlin.inject.anvil.compiler.ksp)
        }
    }
}

android {
    namespace = "com.example.kiaerror"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
