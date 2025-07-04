plugins {
    id("com.microej.gradle.veeport") version libs.versions.microej.sdk
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    microejArchitecture(libs.architecture)

    // Only 3 packs are installed by default: UI, FS and NET.
    // Comment/uncomment the packs depending on your needs.
    // If the UI pack is removed, do not forget to remove the related
    // dependencies and configuration in the Front Panel project.
    microejPack(libs.pack.ui.architecture)
    microejPack(libs.pack.fs)
    microejPack(libs.pack.net)
    // microejPack(libs.pack.audio)
    // microejPack(libs.pack.bluetooth)
    // microejPack(libs.pack.device)
    // microejPack(libs.pack.ecom.network)
    // microejPack(libs.pack.ecom.wifi)
    // microejPack(libs.pack.gnss)
    // microejPack(libs.pack.vg)
    // microejPack(libs.pack.watchdog.timer)

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
