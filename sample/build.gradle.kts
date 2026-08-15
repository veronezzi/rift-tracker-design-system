// AGP 9.x embute o Kotlin — não aplique org.jetbrains.kotlin.android por
// cima (mesma regra do módulo raiz, ver build.gradle.kts).
plugins {
    // Sem versão: o módulo raiz (com.android.library) já fixa 9.1.1 no
    // classpath, pinar de novo aqui gera conflito de resolução do plugin.
    id("com.android.application")
}

android {
    namespace = "com.rifttracker.designsystem.sample"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.rifttracker.designsystem.sample"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = false
        viewBinding = true
    }
}

dependencies {
    // Mesmo repositório: usa o módulo raiz direto, sem passar por JitPack.
    implementation(project(":"))
    implementation("androidx.appcompat:appcompat:1.7.0")
}
