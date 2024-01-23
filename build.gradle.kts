plugins {
    id("idea")

    id("java")
    id("java-library")
    id("maven-publish")

    id("org.jetbrains.kotlin.jvm") version "2.0.0-Beta2"
}

group = "rip.lunarydess"
version = "0.0.0-dev"

repositories {
    mavenCentral()
}


fun <V> prop(value: String): V {
    return properties.getValue(value) as V
}

dependencies {
    compileOnly(
        group = "org.jetbrains",
        name = "annotations",
        version = prop("ver_ann-jbr")
    )

    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter-api",
        version = prop("ver_junit")
    )
    testRuntimeOnly(
        group = "org.junit.jupiter",
        name = "junit-jupiter-engine",
        version = prop("ver_junit")
    )
    testRuntimeOnly(
        group = "org.junit.platform",
        name = "junit-platform-console",
        version = prop("ver_junit-plat")
    )
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
}

tasks.withType<Test> { useJUnitPlatform() }


publishing {
    publications {
        create<MavenPublication>("jitpack") {
            from(components["java"])

            groupId = "com.github.lunarydess"
            artifactId = "lilith"
            version = project.toString()

            pom {
                name.set("Lilith")
                description.set("A general purpose library in Java ft. Kotlin extensions.")
                url.set("https://github.com/lunarydess/Library-Lilith-JVM")
                licenses {
                    license {
                        name.set("Apache 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("lunarydess")
                        name.set("Lucielle R. H.")
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/lunarydess/Library-Lilith.git"
                    developerConnection = "scm:git:ssh://github.com/lunarydess/Library-Lilith.git"
                    url = "github.com/lunarydess/Library-Lilith"
                }
            }
        }
    }
    repositories {
        maven {
            name = "JitPack"
            url = uri("https://jitpack.io")
        }
    }
}
