plugins {
    id("com.microej.gradle.mock")
}

microej {
    skippedCheckers = "readme,nullanalysis"
    additionalFilesDir.set(rootProject.layout.projectDirectory)
}

dependencies{
    compileOnly(libs.mock.api)
}
