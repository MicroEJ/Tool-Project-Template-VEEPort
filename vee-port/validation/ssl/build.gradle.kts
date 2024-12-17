import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

group = "com.microej.testsuite"
version = "1.0.2"

microej {
    architectureUsage = System.getProperty("com.microej.architecture.usage") ?: "eval" // or "prod"
    skippedCheckers = "readme,license"
}

dependencies {
    // Define the VEE Port absolute path here if you test a VEE Port already built
    microejVee(files("[vee-port-absolute-path]"))
    // Uncomment this line and comment the line above it this testsuite project is a part of the VEE Port project
    // microejVee(project(":[vee-port-configuration-project]"))
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            microej.useMicroejTestEngine(this, TestTarget.EMB, TestMode.MAIN)

            dependencies {
                implementation(project())
                implementation(libs.junit)
                implementation(libs.junit.platform)

                implementation(libs.api.edc)
                implementation(libs.api.bon)
                implementation(libs.api.ssl)
                implementation(libs.api.net)
                implementation(libs.testsuite.ssl)
                implementation(libs.testsuite.ssl.openjdk)
                // Add the following dependency if running the testsuite over Wi-Fi.
                // implementation(libs.net.wifi.testsuite)
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            includeTestsMatching("Test*")
                            excludeTestsMatching("*AllTestClasses")
                            excludeTestsMatching("*SingleTest*")
                            // PKI tests are used to check certificate chain validation policies
                            excludeTestsMatching("*TestHandshakePki*")
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")
                        }
                    }
                }
            }
        }
    }
}