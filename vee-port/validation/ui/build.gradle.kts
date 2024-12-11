import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

group = "com.microej.testsuite"
version = "1.8.0"

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
                implementation("ej.api:edc:1.3.5")
                implementation("ej.library.test:junit:1.7.1")
                implementation("org.junit.platform:junit-platform-launcher:1.8.2")

                implementation("ej.api:microui:3.5.0")
                implementation("ej.api:drawing:1.0.5")
                implementation("com.microej.pack.ui:ui-testsuite:14.2.0")
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            excludeTestsMatching("*AllTestClasses")
                            excludeTestsMatching("*SingleTest*")
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")
                            // uncomment this line to fully exclude the GPU tests (useless when there is no GPU on the target)
                            //excludeTestsMatching("com.microej.microui.test.gpu*")
                        }
                    }
                }
            }
        }
    }
}