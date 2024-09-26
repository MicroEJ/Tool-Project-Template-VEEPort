plugins {
    id("com.microej.gradle.mock-frontpanel")
}

group = "com.mycompany"
version = "0.1.0-RC"

dependencies {
    implementation("ej.tool.frontpanel:framework:1.1.0")
    implementation("com.microej.pack.ui:ui-pack:14.0.1") {
        artifact {
            name = "frontpanel"
            extension = "jar"
        }
    }
}
