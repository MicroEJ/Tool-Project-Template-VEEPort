import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

group = "com.microej.testsuite"
version = "1.0.1"

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

                implementation(libs.edc)
                implementation(libs.bon)
                implementation(libs.watchdog.timer)
                implementation(libs.watchdog.timer.testsuite)
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            excludeTestsMatching("*SingleTest*")
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")
                            // This test does not work in pqt mode
                            excludeTestsMatching("*testSimpleUseCaseWatchdogTimer*")
                        }
                    }
                }
            }
        }
    }
}