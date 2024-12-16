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
    implementation(libs.edc)
    implementation(libs.microui)
    microejVee(project(":vee-port"))
}