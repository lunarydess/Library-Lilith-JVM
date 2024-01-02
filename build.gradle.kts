plugins {
    id("java")
}

group = "rip.lunarydess"
version = "0.0.0-dev"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(group = "org.jetbrains", name = "annotations", version = "24.1.0")
}
