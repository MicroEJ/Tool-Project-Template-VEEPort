# WatchdogTimer Test Suite

This folder contains a ready-to-use project for testing [Watchdog Timer](https://docs.microej.com/en/latest/VEEPortingGuide/watchdog-timer.html) implementations on a device.
This Test Suite will typically test Watchdog Timer .

## Specifications

- Tested Foundation Library: [Watchdog Timer](https://repository.microej.com/modules/ej/api/watchdog-timer/)
- Test Suite Module: [com.microej.pack.watchdog-timer#watchdog-timer-testsuite#3.0.0](https://repository.microej.com/modules/com/microej/pack/watchdog-timer/watchdog-timer-testsuite/3.0.0/)

Update the Watchdog Timer Test Suite module version in the [build.gradle.kts](build.gradle.kts) to match the requirement of the tested VEE Port.

Please refer to [VEE Port Qualification Test Suite Versioning](https://docs.microej.com/en/latest/VEEPortingGuide/veePortQualification.html#test-suite-versioning)
to determine the Watchdog Timer Test Suite module version.

## Requirements

- See VEE Port Test Suites [documentation](../README.md).

## Usage

- In the SDK, import the `java-testsuite-runner-watchdog-timer` project in your workspace.
- Follow the configuration and execution steps described in VEE Port Test Suites [documentation](../README.md).

## Test Suite Properties

- `watchdog.timer.testsuite.is.mode.interrupt`: Set this property to true if the watchdog launches an interrupt instead of a reset when watchdog triggers. (default: false)

## Test Suite Source Code Navigation

See VEE Port Test Suites [documentation](../README.md).

## Troubleshooting

See VEE Port Test Suites [documentation](../README.md).
