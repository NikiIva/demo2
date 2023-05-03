import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                //orianna api
                implementation("com.merakianalytics.orianna:orianna:4.0.0-rc8")
                //  для системных утилит
                implementation("org.apache.commons:commons-lang3:3.12.0")
                // для http запросов
                implementation("org.apache.httpcomponents:httpclient:4.5.14")
                // для работы с JSON (POJO <-> JSON)
                implementation("com.fasterxml.jackson.core:jackson-core:2.15.0")
                // для работы с JSON
                implementation("com.google.code.gson:gson:2.10.1")

            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo2"
            packageVersion = "1.0.0"
        }
    }
}
