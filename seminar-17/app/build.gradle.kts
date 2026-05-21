plugins {
    id("application")
}

application {
    mainClass.set("Main")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":strategy"))
    implementation(project(":factory-method"))
    implementation(project(":abstract-factory"))
    implementation(project(":builder"))
    implementation(project(":prototype"))
    implementation(project(":adapter"))
    implementation(project(":decorator"))
    implementation(project(":facade"))
    implementation(project(":observer"))
    implementation(project(":command"))
}
