
import org.jetbrains.kotlin.ir.backend.js.compile
repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "com.caoyang"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("PrivacySentryPlugin") {
            id = "com.caoyang.trace.privacy"
            implementationClass = "com.example.privacy_detect_plugins.plugins.privacy.PrivacySentryPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "privacy-plugin"
            version = version

            from(components["java"])
        }
    }
    repositories {
        maven{
            url = uri(layout.buildDirectory.dir("maven-repo"))
        }
        mavenLocal()
    }
}

dependencies {
    val agpVersion = "8.5.0"
    val kotlinVersion = "1.9.22"
    implementation("com.android.tools.build:gradle:${agpVersion}")
    implementation("com.android.tools.build:gradle-api:${agpVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:${kotlinVersion}")
    implementation("org.ow2.asm:asm-commons:9.6")
    implementation("commons-io:commons-io:2.15.0")
}