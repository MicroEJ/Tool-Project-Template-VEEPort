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
    implementation(libs.edc)
    implementation(libs.microui)
    implementation(libs.fs)
    implementation(libs.net)
    implementation(libs.ssl)
    microejVee(project(":vee-port"))
}