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
    // Use a VEE Port included in the same multi-project as this validation application
    microejVee(project(":vee-port"))

    // Use a published version of a VEE Port
    // or a local VEE Port project with includeBuild directive
    // microejVee("com.mycompany:vee-port:1.0.0")

    // Use a built VEE Port (can be used for SDK5 compatibility)
    // In this case, architectureUsage is determined by the VEE Port
    // Use double "\" on Windows
    // microejVee(files("/path/to/vee-port/build/veePort/source"))
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