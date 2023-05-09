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
        jvmToolchain(11)
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
                // https://mvnrepository.com/artifact/io.kotest/kotest-assertions-core-jvm
//                implementation("io.kotest:kotest-assertions-core-jvm:5.6.1")
                // мокито
//                implementation("org.mockito:mockito-core:5.3.1")
                // junit 5
//                implementation("org.junit.jupiter:junit-jupiter-engine:5.0.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
//                implementation(project(":core"))
//                implementation(kotlin("test-junit5"))
//                implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")

//                implementation("org.spekframework.spek2:spek-dsl-jvm:$spek2Version")
//                runtimeOnly("org.spekframework.spek2:spek-runtime-jvm:$spek2Version")
//                runtimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek2Version")


//                implementation("org.xmlunit:xmlunit-core:2.6.0")

//                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
//                implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
//                runtimeOnly("com.fasterxml.woodstox:woodstox-core:5.0.3")


            }
        }
    }
}



//tasks {
//    // Use the native JUnit support of Gradle.
//    "test"(Test::class) {
//        useJUnitPlatform()
//    }
//}

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
