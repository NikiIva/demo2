import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.psi.psiUtil.checkReservedPrefixWord
import java.util.*

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
        jvmToolchain(17)
        withJava()
    }
    sourceSets {

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                //orianna api
//                implementation("com.merakianalytics.orianna:orianna:4.0.0-rc8")
                //  для системных утилит
                implementation("org.apache.commons:commons-lang3:3.12.0")
                // для http запросов
                implementation("org.apache.httpcomponents:httpclient:4.5.14")
                // для работы с JSON (POJO <-> JSON)
                implementation("com.fasterxml.jackson.core:jackson-core:2.15.0")
                // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
                implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")

                // для работы с JSON
                implementation("com.google.code.gson:gson:2.10.1")
                // sqlite
                implementation("org.xerial:sqlite-jdbc:3.41.2.1")

                implementation("io.coil-kt:coil-compose:2.0.0-rc01")

                // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
                implementation("com.github.bumptech.glide:glide:4.15.1")

                // https://mvnrepository.com/artifact/com.caverock/androidsvg
                implementation("com.caverock:androidsvg:1.4")
                // https://mvnrepository.com/artifact/org.apache.xmlgraphics/batik-transcoder
                implementation("org.apache.xmlgraphics:batik-transcoder:1.16")




            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }
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
