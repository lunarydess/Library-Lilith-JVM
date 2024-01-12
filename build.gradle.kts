plugins {
    id("idea")

    id("java")
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "2.0.0-Beta2"
}

group = "rip.lunarydess"
version = "0.0.0-dev"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(
        group = "org.jetbrains",
        name = "annotations",
        version = "24.1.0"
    )

    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter-api",
        version = "5.10.1"
    )
    testRuntimeOnly(
        group = "org.junit.jupiter",
        name = "junit-jupiter-engine",
        version = "5.10.1"
    )
    testRuntimeOnly(
        group = "org.junit.platform",
        name = "junit-platform-console",
        version = "1.10.1"
    )
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
}

tasks.withType<Test> { useJUnitPlatform() }
