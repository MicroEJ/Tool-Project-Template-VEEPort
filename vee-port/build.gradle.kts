plugins {
    id("com.microej.gradle.veeport") version libs.versions.microej.sdk
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
    // Uncomment the packs you need
    // microejPack(libs.pack.audio)
    // microejPack(libs.pack.bluetooth)
    // microejPack(libs.pack.device)
    // microejPack(libs.pack.ecom.network)
    // microejPack(libs.pack.ecom.wifi)
    // microejPack(libs.pack.vg)
    // microejPack(libs.pack.watchdog.timer)

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
