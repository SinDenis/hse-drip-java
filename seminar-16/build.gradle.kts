plugins {
    id("java")
    id("application")
}

group = "ru.centraluniversity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("com.h2database:h2:2.2.224")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("Main")
}

tasks.register("printProjectInfo") {
    doLast {
        println("name=${project.name}, group=${project.group}, version=${project.version}")
    }
}

tasks.test {
    useJUnitPlatform()
}
