# Serial Test Suite

This folder contains a ready-to-use project for testing [Serial](https://docs.microej.com/en/latest/VEEPortingGuide/serial.html) implementations on a device.
This Test Suite will typically test the following serial operations: open, close, read, write.

## Specifications

- Tested Foundation Library: `Serial <https://repository.microej.com/modules/ej/api/serial/>`_
- Test Suite Module: `com.microej.pack.serial#serial-testsuite <https://repository.microej.com/modules/com/microej/pack/serial/serial-testsuite/>`_

Set the Test Suite's module version in [build.gradle.kts](build.gradle.kts) to match the version of the pack included in the VEE Port tested.

Please refer to [VEE Port Qualification Test Suite Versioning](https://docs.microej.com/en/latest/VEEPortingGuide/veePortQualification.html#test-suite-versioning)
to determine the Serial Test Suite module version.

## Requirements

- Read VEE Port Test Suites [documentation](../README.md).

## Tests properties

Before running tests, please fill in the following fields in file `validation/microej-testsuite-common.properties` with expected values:

-  **microej.java.property.serial.port.rx**: [*MANDATORY*] set port used for rx operations (both tx and rx can get mapped to the same port so as to test serial in loopback mode).
-  **microej.java.property.serial.port.tx**: [*MANDATORY*] set port used for tx operations (both tx and rx can get mapped to the same port so as to test serial in loopback mode).
-  **microej.java.property.serial.port.baudrate**: [*OPTIONAL*] set baudrate of rx/tx ports (default value: 115200).
-  **microej.java.property.serial.port.bitsperchar**: [*OPTIONAL*] set data bits of rx/tx ports (default value: 8).
-  **microej.java.property.serial.port.stopbits**: [*OPTIONAL*] set stop bits of rx/tx ports (default value: 1).
-  **microej.java.property.serial.port.parity**: [*OPTIONAL*] set parity of rx/tx ports (default value: 0).

## Usage

- Follow the configuration and execution steps described in VEE Port Test Suites [documentation](../README.md).

## Test Suite Source Code Navigation

- See VEE Port Test Suites [documentation](../README.md).

## Troubleshooting

- See Vee Port Test Suites [documentation](../README.md).
