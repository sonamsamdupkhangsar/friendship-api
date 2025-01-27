import org.springframework.boot.gradle.tasks.bundling.BootJar

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.4/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("org.springframework.boot") version "3.4.1"  // Replace with your desired version
    id("io.spring.dependency-management") version "1.1.6" // Dependency management plugin
    id("maven-publish")
}

dependencyManagement {
    imports {
        mavenBom ("org.springframework.cloud:spring-cloud-dependencies:2024.0.0")
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://maven.pkg.github.com/sonamsamdupkhangsar/webclients")
        credentials {
            username = System.getenv("USERNAME")
            password = System.getenv("PERSONAL_ACCESS_TOKEN")
        }
    }
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:32.1.1-jre")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-all:3.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.security:spring-security-oauth2-client")
    implementation ("org.springframework.security:spring-security-oauth2-resource-server")
    implementation("io.projectreactor:reactor-test")
    testImplementation("io.r2dbc:r2dbc-h2:1.0.0.RELEASE")
    testImplementation("com.h2database:h2:2.2.220")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.0.1")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("me.sonam:webclients:1.0.0-SNAPSHOT")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}


tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "cloud.sonam"
            artifactId = "friendships-api"
            version = "1.0.0-SNAPSHOT"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sonamsamdupkhangsar/friendships-api")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("PERSONAL_ACCESS_TOKEN")
            }
        }
    }

}


