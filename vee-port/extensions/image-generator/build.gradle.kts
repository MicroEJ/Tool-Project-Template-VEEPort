plugins {
    id("com.microej.gradle.jse-library")
}

microej {
    skippedCheckers = "readme"
    additionalFilesDir.set(rootProject.layout.projectDirectory)
}

dependencies {
    implementation(libs.pack.ui) {
        artifact {
            name = "imageGenerator"
            extension = "jar"
        }
    }
}