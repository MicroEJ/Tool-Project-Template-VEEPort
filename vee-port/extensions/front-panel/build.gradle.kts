plugins {
    id("com.microej.gradle.mock-frontpanel")
}

microej {
    skippedCheckers = "readme,changelog,license"
}

dependencies {
    implementation(libs.frontpanel.framework)
    // Remove this dependency if you remove the UI pack
    implementation(libs.frontpanel.ui.widget)
}
