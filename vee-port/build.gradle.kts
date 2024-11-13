plugins {
    id("com.microej.gradle.veeport") version "0.20.0"
}

dependencies {
    microejArchitecture("com.microej.architecture.CM4.CM4hardfp_GCC48:flopi4G25:8.2.0")

    //Uncomment the microejPack dependency to set a Pack
    //microejPack("com.mycompany:mypack:1.0.0")

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
