plugins {
    id("com.microej.gradle.application") version "0.20.0"
}

microej {
    skippedCheckers = "readme,changelog,license,nullanalysis"
}

microej {
    applicationEntryPoint = "com.mycompany.myapplication.Main"
    architectureUsage = System.getProperty("com.microej.architecture.usage") ?: "eval" // or "prod"
}

dependencies {
    implementation("ej.api:edc:1.3.6")
    microejVee(project(":vee-port"))
}