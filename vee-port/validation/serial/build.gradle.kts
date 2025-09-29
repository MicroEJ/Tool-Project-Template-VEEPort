import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

microej {
    architectureUsage = System.getProperty("com.microej.architecture.usage") ?: "eval" // or "prod"
    skippedCheckers = "readme,license,changelog"
}

dependencies {
    // Define the VEE Port absolute path here if you test a VEE Port already built
    // microejVee(files("[vee-port-absolute-path]"))
    // Uncomment this line and comment the line above it this testsuite project is a part of the VEE Port project
    microejVee(project(veePortProject))
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            microej.useMicroejTestEngine(this, TestTarget.EMB, TestMode.MAIN)

            dependencies {
                implementation(project())
                implementation(libs.ej.junit)
                implementation(libs.junit.platform)

                implementation(libs.testsuite.serial)
                implementation(libs.ej.edc)
                implementation(libs.ej.bon)
                implementation(libs.ej.serial)
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")
                            excludeTestsMatching("*AbstractTest*")
                            excludeTestsMatching("*AllTestClasses")
							
                            includeTestsMatching("*SingleTest_*")
                        }
                    }
                }
            }
        }
    }
}
