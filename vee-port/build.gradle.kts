plugins {
    id("com.microej.gradle.veeport") version "0.20.0"
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    microejArchitecture(libs.architecture)

    // Comment the packs you don't need
    microejPack(libs.pack.ui.architecture)
    microejPack(libs.pack.fs)
    microejPack(libs.pack.net)

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
