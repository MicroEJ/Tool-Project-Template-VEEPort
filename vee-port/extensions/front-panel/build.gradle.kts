plugins {
    id("com.microej.gradle.mock-frontpanel")
}

microej {
    skippedCheckers = "readme"
    additionalFilesDir.set(rootProject.layout.projectDirectory)
}

dependencies {
    implementation(libs.frontpanel.framework)
    // Remove this dependency if you remove the UI pack
    implementation(libs.frontpanel.ui.widget)
}
