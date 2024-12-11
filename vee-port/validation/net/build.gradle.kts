/*
 * Kotlin
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * This library is provided in source code for use, modification and test, subject to license terms.
 * Any modification of the source code will break MicroEJ Corp. warranties on the whole library.
 */
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
                implementation("ej.library.test:junit:1.7.1")
                implementation("org.junit.platform:junit-platform-launcher:1.8.2")

                implementation("com.microej.pack.net:net-1_1-testsuite:4.1.1")
                implementation("com.microej.pack.net:net-1_1-testsuite-openjdk:1.1.1")
                implementation("ej.api:net:1.1.1")

                // Add the following dependency if running the testsuite over Wi-Fi.
                // implementation("com.microej.pack.net:net-1_1-testsuite-wifi:2.1.1")
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
                            // do not pass standalone tests, only group tests
                            excludeTestsMatching("com.microej.net.test.standalone*")
                        }
                    }
                }
            }
        }
    }
}