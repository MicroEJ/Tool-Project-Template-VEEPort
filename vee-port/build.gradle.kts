plugins {
    id("com.microej.gradle.veeport") version "0.20.0"
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    microejArchitecture(libs.architecture)

    //Uncomment the microejPack dependency to set a Pack
    //microejPack("com.mycompany:mypack:1.0.0")

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
