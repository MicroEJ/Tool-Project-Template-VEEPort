plugins {
    id("com.microej.gradle.application") version "0.20.0"
}

microej {
    applicationEntryPoint = "com.mycompany.myapplication.Main"
    architectureUsage = "eval" // or "prod"
}

dependencies {
    implementation("ej.api:edc:1.3.6")
    microejVee(project(":vee-port"))
}