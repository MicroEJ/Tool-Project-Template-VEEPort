plugins {
    id("com.microej.gradle.application") version "0.19.0"
}

group="com.mycompany"
version="0.1.0-RC"

microej {
    applicationEntryPoint = "com.mycompany.myapplication.Main"
    architectureUsage = "eval" // or "prod"
}

dependencies {
    implementation("ej.api:edc:1.3.6")
    microejVee(project(":vee-port"))
}