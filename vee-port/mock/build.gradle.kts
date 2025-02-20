plugins {
    id("com.microej.gradle.mock")
}

microej {
    skippedCheckers = "readme,changelog,license,nullanalysis"
}

dependencies{

    compileOnly(libs.mock.api)

}
