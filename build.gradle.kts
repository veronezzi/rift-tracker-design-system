// AGP 9.x embute o Kotlin — não aplique org.jetbrains.kotlin.android por
// cima, duplicar gera conflito (mesma regra do rift-tracker, ver stack.md).
plugins {
    id("com.android.library") version "9.1.1"
    `maven-publish`
}

// JitPack sobrescreve isso automaticamente pelo nome da tag no momento do
// build remoto — este valor só importa pra testar localmente
// (./gradlew publishToMavenLocal).
version = "1.0.0"

android {
    namespace = "com.rifttracker.designsystem"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
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

    publishing {
        singleVariant("release")
    }
}

dependencies {
    // "api" (não "implementation"): quem depender desta lib ganha o Material
    // Components de graça, porque os estilos daqui (Widget.RiftTracker.*)
    // são construídos em cima dele — sem isso, o consumidor teria que
    // adicionar a mesma dependência na mão pra o tema nem resolver.
    api("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-ktx:1.19.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.veronezzi"
            artifactId = "rift-tracker-design-system"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
