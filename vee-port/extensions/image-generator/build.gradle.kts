plugins {
    id("com.microej.gradle.j2se-library")
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    implementation("com.microej.pack.ui:ui-pack:14.0.1") {
        artifact {
            name = "imageGenerator"
            extension = "jar"
        }
    }
}