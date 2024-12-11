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
version = "1.1.0"

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

                implementation("com.microej.pack.fs:fs-testsuite:3.0.8")
                implementation("com.microej.pack.fs:fs-testsuite-openjdk:1.0.1")
                implementation("ej.api:edc:1.3.2")
                implementation("ej.api:bon:1.3.0")
                implementation("ej.api:fs:2.1.0")
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