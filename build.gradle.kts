import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("kapt") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	kotlin("plugin.jpa") version "1.5.21"
}

val hibernateTypesVersion = "2.10.4"
val commonMarkVersion = "0.17.1"
val mapstructVersion = "1.4.2.Final"
var liquibaseVersion = "4.4.3"

group = "com.igorivkin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	jcenter()
	mavenCentral()
	maven(url = "https://dl.bintray.com/jetbrains/markdown")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.postgresql:postgresql")
	implementation("com.vladmihalcea:hibernate-types-52:${hibernateTypesVersion}")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.liquibase:liquibase-core:${liquibaseVersion}")

	// CommonMark for Markdown
	implementation("org.commonmark:commonmark:${commonMarkVersion}")
	implementation("org.commonmark:commonmark-ext-gfm-tables:${commonMarkVersion}")

	// MapStruct
	implementation("org.mapstruct:mapstruct:${mapstructVersion}")
	kapt("org.mapstruct:mapstruct-processor:${mapstructVersion}")

	// Testing purposes
	testImplementation("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
