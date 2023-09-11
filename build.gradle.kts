import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.0-M2"
  id("io.spring.dependency-management") version "1.1.3"
  id("org.graalvm.buildtools.native") version "0.9.24"
  kotlin("jvm") version "1.9.10"
  kotlin("plugin.spring") version "1.9.10"
}

group = "dev.axgr"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springShellVersion"] = "3.2.0-M1"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.shell:spring-shell-starter")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.shell:spring-shell-dependencies:${property("springShellVersion")}")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
