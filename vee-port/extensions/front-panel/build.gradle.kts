plugins {
    id("com.microej.gradle.mock-frontpanel")
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    implementation(libs.frontpanel.framework)
    implementation(libs.pack.ui) {
        artifact {
            name = "frontpanel"
            extension = "jar"
        }
    }
}
