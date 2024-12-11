# MicroEJ Core Validation

## Overview

This project contains test cases aimed at validating the correct runtime execution 
of a VEE Port connected to a Board Support Package (BSP).
 
Tests are written as JUnit test cases, thus the main entry point is automatically generated by the SDK.

Tests can be launched:

-  as a standard Application by using a local launcher.
-  as a VEE Port Test Suite by building the module.

## Requirements

- A VEE Port built from an Architecture version `7.0.0` or higher.
- See VEE Port Test Suites [documentation](../README.md).

## Usage

### Launcher Mode

- Follow the configuration and execution steps described in VEE Port Test Suites [documentation](../README.md).

-  Before linking the application against the VEE Port, add the
   following code in your BSP to test the FPU configuration and SNI native
   calling convention:

```c
#include "sni.h"

jfloat Java_com_microej_core_tests_MicroejCoreValidation_testFloat(jfloat a, jfloat b){
   return a * b;
}

jdouble Java_com_microej_core_tests_MicroejCoreValidation_testDouble(jdouble a, jdouble b){
   return a * b;
}

jint Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments01(jint i1, jint i2, jint i3, jint i4, jint i5, jint i6, jint i7, jint i8, jint i9, jint i10){
   if(i1==0x01020304 && 
      i2==0x05060708 && 
      i3==0x090A0B0C && 
      i4==0x0D0E0F10 && 
      i5==0x11121314 && 
      i6==0x15161718 && 
      i7==0x191A1B1C && 
      i8==0x1D1E1F20 && 
      i9==0x21222324 && 
      i10==0x25262728){
       return 0x292A2B2C;
   }
   else {
       return 0;
   }		
}

jlong Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments02(jlong l1, jlong l2, jlong l3, jlong l4, jlong l5, jlong l6, jlong l7, jlong l8, jlong l9, jlong l10){
   if(l1==0x2D2E2F3031323334ll && 
      l2==0x35363738393A3B3Cll && 
      l3==0x3D3E3F4041424344ll && 
      l4==0x45464748494A4B4Cll && 
      l5==0x4D4E4F5051525354ll && 
      l6==0x55565758595A5B5Cll && 
      l7==0x5D5E5F6061626364ll && 
      l8==0x65666768696A6B6Cll && 
      l9==0x6D6E6F7071727374ll && 
      l10==0x75767778797A7B7Cll){
       return 0x7D7E7F8081828384ll;
   }
   else {
       return 0ll;
   }		
}

jlong Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments03(jint i1, jlong l2, jint i3, jlong l4, jint i5, jlong l6, jint i7, jlong l8, jint i9, jlong l10){
   if(i1==0x85868788 && 
      l2==0x898A8B8C8D8E8F90ll && 
      i3==0x91929394 && 
      l4==0x95969798999A9B9Cll && 
      i5==0x9D9E9FA0 && 
      l6==0xA1A2A3A4A5A6A7A8ll && 
      i7==0xA9AAABAC && 
      l8==0xADAEAFB0B1B2B3B4ll && 
      i9==0xB5B6B7B8 && 
      l10==0xB9BABBBCBDBEBFC0ll){
       return 0xC1C2C3C4C5C6C7C8ll;
   }
   else {
       return 0ll;
   }		
}

jfloat Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments04(jfloat f1, jfloat f2, jfloat f3, jfloat f4, jfloat f5, jfloat f6, jfloat f7, jfloat f8, jfloat f9, jfloat f10){
   if(f1==1.0f && 
      f2==1.1f && 
      f3==1.2f && 
      f4==1.3f && 
      f5==1.4f && 
      f6==1.5f && 
      f7==1.6f && 
      f8==1.7f && 
      f9==1.8f && 
      f10==1.9f){
       return 2.0f;
   }
   else {
       return 0.0f;
   }		
}

jdouble Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments05(jdouble d1, jdouble d2, jdouble d3, jdouble d4, jdouble d5, jdouble d6, jdouble d7, jdouble d8, jdouble d9, jdouble d10){
   if(d1==2.0 && 
      d2==2.1 && 
      d3==2.2 && 
      d4==2.3 && 
      d5==2.4 && 
      d6==2.5 && 
      d7==2.6 && 
      d8==2.7 && 
      d9==2.8 && 
      d10==2.9){
       return 3.0;
   }
   else {
       return 0.0;
   }		
}

jdouble Java_com_microej_core_tests_MicroejCoreValidation_testNativeArguments06(jfloat f1, jdouble d2, jfloat f3, jdouble d4, jfloat f5, jdouble d6, jfloat f7, jdouble d8, jfloat f9, jdouble d10){
   if(f1==3.0f && 
      d2==3.1 && 
      f3==3.2f && 
      d4==3.3 && 
      f5==3.4f && 
      d6==3.5 && 
      f7==3.6f && 
      d8==3.7 && 
      f9==3.8f && 
      d10==3.9){
       return 4.0;
   }
   else {
       return 0.0;
   }		
}
```

-  For a best result in the Java Round Robin test, disable all the C
   native tasks except the MicroEJ task.

-  Once all the tests have passed successfully, MicroEJ Core is validated.

-  See below for an output example of a successful validation.

```
MicroEJ START
*****************************************************************************************************
*                                  MicroEJ Core Validation - 3.2.0                                  *
*****************************************************************************************************
* Copyright 2013-2023 MicroEJ Corp. All rights reserved.                                            *
* Use of this source code is governed by a BSD-style license that can be found with this software.  *
*****************************************************************************************************

-> Check visible clock (LLMJVM_IMPL_getCurrentTime validation)...
Property 'com.microej.core.tests.max.allowed.clock.tick.duration.milliseconds' is not set (default to '20' millisecondss)
Property 'com.microej.core.tests.clock.seconds' is not set (default to '10' seconds)
1
2
3
4
5
6
7
8
9
10
OK: testVisibleClock
-> Check schedule request and wakeup (LLMJVM_IMPL_scheduleRequest and LLMJVM_IMPL_wakeupVM validation)...
Property 'com.microej.core.tests.max.allowed.clock.tick.duration.milliseconds' is not set (default to '20' millisecondss)
Waiting for 5s...
...done
OK: testTime
-> Check monotonic time (LLMJVM_IMPL_getCurrentTime, LLMJVM_IMPL_setApplicationTime validation)...
Waiting for 5s...
...done
OK: testMonotonicTime
-> Check Java round robin (LLMJVM_IMPL_scheduleRequest validation)...
For a best result, please disable all the C native tasks except the MicroEJ task.
Task 3 is waiting for start...
Task 2 is waiting for start...
Task 1 is waiting for start...
Task 0 is waiting for start...
Starting tasks and wait for 10 seconds...
Task 2 ends.
Task 3 ends.
Task 0 ends.
Task 1 ends.
...done.
OK: testJavaRoundRobin
Main thread starts sleeping for 1s..
WaitMaxTimeThread starts sleeping for `Long.MAX_VALUE` milliseconds
Main thread woke up!
OK: testScheduleMaxTime
-> Check isInReadOnlyMemory (LLBSP_IMPL_isInReadOnlyMemory validation)...
Test synchronize on literal string
Test synchronize on class
Test multiple synchronize
OK: testIsInReadOnlyMemory
-> Check FPU (soft/hard FP option)...
OK: testFPU
-> Check floating-point arithmetic with NaN...
-> Check floating-point arithmetic with 0.0 and -0.0...
-> Check floating-point arithmetic with infinity...
-> Check floating-point arithmetic with min values...
-> Check floating-point division by 0.0...
-> Check floating-point Math functions...
-> Check integer arithmetic...
OK: testFloatingPointArithmetic
-> Check floating-point parser...
OK: testParseFloatingPoint
-> Check floating-point formatter...
OK: testFormatFloatingPoint
-> Check parsing a string as a double ; in some systems such operations may allocate memory in the C heap (strtod, strtof, malloc implementation)...
OK: testParseDoubleStringHeap
Property 'com.microej.core.tests.monotonic.time.check.seconds' is not set (default to '60' seconds)
-> Check monotonic time consistency for 60 seconds (LLMJVM_IMPL_getCurrentTime)...
.............................
OK: testMonotonicTimeIncreases
-> Check current time clock tick duration (LLMJVM_IMPL_getCurrentTime, LLMJVM_IMPL_getTimeNanos)...
Property 'com.microej.core.tests.max.allowed.clock.tick.duration.milliseconds' is not set (default to '20' millisecondss)
Estimated LLMJVM_IMPL_getCurrentTime clock tick is 1 ms.
Estimated LLMJVM_IMPL_getTimeNanos clock tick is lower than 4000 ns.
OK: testSystemCurrentTimeClockTick
-> Check schedule request clock tick duration (LLMJVM_IMPL_scheduleRequest)...
Property 'com.microej.core.tests.max.allowed.clock.tick.duration.milliseconds' is not set (default to '20' millisecondss)
Estimated LLMJVM_IMPL_scheduleRequest clock tick is 1 ms.
OK: testScheduleRequestClockTick
-> Check SNI native calling convention (ABI)...
OK: testSniAbi
PASSED: 15
MicroEJ END (exit code = 0)
```

### VEE Port Test Suite Mode

-  Follow the configuration and execution steps described in VEE Port Test Suites [documentation](../README.md).

## Dependencies

*All dependencies are retrieved transitively by Gradle*.

## Troubleshooting

### The test blocks during the Java round robin test under FreeRTOS

Issue: 

- The test of `LLMJVM_IMPL_scheduleRequest` blocks at the following
  step: `Starting tasks and wait for 10 seconds...`.

Solution: 

- Ensure the JVM native C task has a priority lower than the FreeRTOS
  timer task defined in `FreeRTOSConfig.h` (`configTIMER_TASK_PRIORITY`).

### VEE Port Test Suite issues

See VEE Port Test Suites [documentation](../README.md).

## Source

N/A

## Restrictions

None.