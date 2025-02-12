import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

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

                implementation(libs.testsuite.fs)
                implementation(libs.testsuite.fs.openjdk)
                implementation(libs.api.edc)
                implementation(libs.api.bon)
                implementation(libs.api.fs)
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            includeTestsMatching("Test*")
                            excludeTestsMatching("*AllTestClasses")
                            excludeTestsMatching("*SingleTest*")
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")

                            // Including this test throws a "ClassNotFoundException: ej.lang.ResourceManager"
                            // because resourcemanager-1.0-api not in the classpath used by JUnit to discover tests classes
                            // because it hab to be removed from the test runtime classpath (see dependencies above). 
                            excludeTestsMatching("TestResources01")
                        }
                    }
                }
            }
        }
    }
}