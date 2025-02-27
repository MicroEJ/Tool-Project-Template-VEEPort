plugins {
    id("com.microej.gradle.jse-library")
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    implementation(libs.pack.ui) {
        artifact {
            name = "imageGenerator"
            extension = "jar"
        }
    }
}