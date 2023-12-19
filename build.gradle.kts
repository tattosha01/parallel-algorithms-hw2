import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("me.champeau.jmh") version "0.7.2"
    application
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    jmhImplementation("org.openjdk.jmh:jmh-core:1.36")
    jmhImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.36")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.23.1")
    implementation("org.jetbrains.kotlinx:atomicfu:0.23.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

jmh {
    jvmArgsAppend.addAll("-Xms10G", "-Xmx10G")
}