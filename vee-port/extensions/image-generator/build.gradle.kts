plugins {
    id("com.microej.gradle.j2se-library")
}

dependencies {
    implementation("com.microej.pack.ui:ui-pack:14.0.1") {
        artifact {
            name = "imageGenerator"
            extension = "jar"
        }
    }
}