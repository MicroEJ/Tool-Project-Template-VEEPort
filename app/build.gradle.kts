plugins {
    id("com.microej.gradle.application") version libs.versions.microej.sdk
}

microej {
    skippedCheckers = "readme,changelog,license,nullanalysis"
}

microej {
    applicationEntryPoint = "com.mycompany.myapplication.Main"
    architectureUsage = System.getProperty("com.microej.architecture.usage") ?: "eval" // or "prod"
}

dependencies {
    implementation(libs.api.edc)
    implementation(libs.api.bon)
    // Remove the API dependency when you remove the related pack
    implementation(libs.api.microui)
    implementation(libs.api.fs)
    implementation(libs.api.net)
    implementation(libs.api.ssl)
    microejVee(project(":vee-port"))
}