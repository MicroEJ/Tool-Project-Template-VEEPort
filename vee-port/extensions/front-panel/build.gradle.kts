plugins {
    id("com.microej.gradle.mock-frontpanel")
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    implementation(libs.frontpanel.widget)
    implementation(libs.frontpanel.framework)
}
