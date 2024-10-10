import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // https://mvnrepository.com/artifact/com.facebook.presto/presto-parser
    implementation("com.facebook.presto:presto-parser:0.280")

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-cli
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

application {
    // Define the main class for the application.
    mainClass.set("presto.query.extractor.AppKt")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
