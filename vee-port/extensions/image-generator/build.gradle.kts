plugins {
    id("com.microej.gradle.j2se-library")
}

group = "com.mycompany"
version = "0.1.0-RC"

dependencies {
    implementation("com.microej.pack.ui:ui-pack:14.0.1") {
        artifact {
            name = "imageGenerator"
            extension = "jar"
        }
    }
}