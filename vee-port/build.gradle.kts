plugins {
    id("com.microej.gradle.veeport") version "0.19.0"
}

group = "com.mycompany"
version = "0.1.0-RC"

dependencies {
    //Uncomment the microejArchitecture dependency to set the Architecture
    //microejArchitecture("com.mycompany:myarchitecture:1.0.0")

    //Uncomment the microejPack dependency to set a Pack
    //microejPack("com.mycompany:mypack:1.0.0")

    microejFrontPanel(project(":vee-port:front-panel"))

    microejMock(project(":vee-port:mock"))

    microejTool(project(":vee-port:image-generator"))
}
