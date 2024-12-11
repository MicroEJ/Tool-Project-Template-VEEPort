import com.microej.gradle.plugins.TestMode
import com.microej.gradle.plugins.TestTarget

plugins {
    id("com.microej.gradle.testsuite")
}

group = "com.microej.testsuite"
version = "1.0.0"

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
                implementation("ej.library.test:junit:1.7.1")
                implementation("org.junit.platform:junit-platform-launcher:1.8.2")

                implementation("com.microej.pack.external-resource:external-resource-testsuite:1.0.2")
                implementation("ej.api:edc:1.3.3")
                implementation("ej.api:bon:1.4.0")
                implementation("ej.api:sni:1.4.0")
                implementation("ej.api:fs:2.0.6")
                implementation("ej.library.eclasspath:logging:1.2.1")
            }

            targets {
                all {
                    testTask.configure {
                        filter {
                            excludeTestsMatching("*AllTestClasses")
                            excludeTestsMatching("*SingleTest*")
                            // do not embed inner classes as test classes
                            excludeTestsMatching("*$*")
                        }
                    }
                }
            }
        }
    }
}