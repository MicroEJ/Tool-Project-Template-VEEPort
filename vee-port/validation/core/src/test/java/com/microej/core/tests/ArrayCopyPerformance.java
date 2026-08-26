/*
 * Java
 *
 * Copyright 2026 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 *
 * Build: 7E4D1F7C
 */
package com.microej.core.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ej.bon.Util;

/**
 * Measures {@link System#arraycopy(Object, int, Object, int, int)} throughput on large {@code byte[]}
 * buffers and checks it against a minimum expected throughput.
 * <p>
 * On byte arrays {@link System#arraycopy(Object, int, Object, int, int)} resolves to a direct tail
 * call into the C library {@code memmove}. Its performance depends on the BSP libc configuration
 * (e.g. newlib-nano versus full newlib) and on whether the BSP overrides {@code memmove} with a word-wide
 * implementation. This test guards against a regression to a slow byte-wise copy.
 * <p>
 * The buffers are far larger than a typical CPU data cache, so the copy exercises real memory
 * bandwidth rather than staying cache-resident. Four copies are measured and each is asserted against
 * its own minimum:
 * <ul>
 * <li>a word-aligned copy between the two buffers, whose throughput drops if the BSP reverts to a
 * byte-wise libc copy;</li>
 * <li>a one-byte misaligned copy between the two buffers, whose throughput additionally drops if the
 * BSP stops overriding {@code memmove} with a word-wide implementation (a word-wide fast path is
 * typically defeated by misalignment);</li>
 * <li>an overlapping copy shifted one byte to the right within a single buffer (destination one byte
 * above the source), which forces {@code memmove} to copy backwards to preserve the overlap; and</li>
 * <li>an overlapping copy shifted one byte to the left within a single buffer (destination one byte
 * below the source), which lets {@code memmove} copy forwards.</li>
 * </ul>
 * <p>
 * The two overlapping copies specifically exercise the direction-picking logic that distinguishes
 * {@code memmove} from a plain {@code memcpy}: a BSP that wrongly routed {@link System#arraycopy} to
 * {@code memcpy} would corrupt the overlapping data, and any per-direction performance asymmetry
 * surfaces here.
 * <p>
 * These figures are only meaningful relative to the platform's theoretical peak: the measured
 * throughputs must be compared against the memory bandwidth the silicon vendor advertises for the
 * backing memory (e.g. the RAM bandwidth figures in the datasheet or reference manual) to judge whether
 * the port actually reaches the expected fraction of peak, rather than merely clearing a fixed
 * regression floor.
 * <p>
 * The minimum expected throughputs (in MB/s) are read from the system properties
 * {@value #PROPERTY_PREFIX}{@value #OPTION_MIN_THROUGHPUT_MBPS} (aligned),
 * {@value #PROPERTY_PREFIX}{@value #OPTION_MIN_THROUGHPUT_MISALIGNED_MBPS} (misaligned),
 * {@value #PROPERTY_PREFIX}{@value #OPTION_MIN_THROUGHPUT_OVERLAP_RIGHT_MBPS} (overlap right) and
 * {@value #PROPERTY_PREFIX}{@value #OPTION_MIN_THROUGHPUT_OVERLAP_LEFT_MBPS} (overlap left). When a
 * property is not set its expectation defaults to zero, so the corresponding check is a no-op on VEE
 * Ports that do not declare an expectation.
 */
public class ArrayCopyPerformance {

	private static final String PROPERTY_PREFIX = "com.microej.core.tests.";

	/**
	 * Option that specifies the minimum expected aligned {@link System#arraycopy} throughput, in MB/s.
	 * When unset, the aligned throughput is only logged and its check passes.
	 */
	private static final String OPTION_MIN_THROUGHPUT_MBPS = "arraycopy.min.throughput.mbps";

	/**
	 * Option that specifies the minimum expected misaligned {@link System#arraycopy} throughput, in
	 * MB/s. When unset, the misaligned throughput is only logged and its check passes.
	 */
	private static final String OPTION_MIN_THROUGHPUT_MISALIGNED_MBPS = "arraycopy.min.throughput.misaligned.mbps";

	/**
	 * Option that specifies the minimum expected throughput, in MB/s, of an overlapping copy shifted
	 * one byte to the right (destination one byte above the source) within a single buffer. When unset,
	 * the throughput is only logged and its check passes.
	 */
	private static final String OPTION_MIN_THROUGHPUT_OVERLAP_RIGHT_MBPS = "arraycopy.min.throughput.overlap.right.mbps";

	/**
	 * Option that specifies the minimum expected throughput, in MB/s, of an overlapping copy shifted
	 * one byte to the left (destination one byte below the source) within a single buffer. When unset,
	 * the throughput is only logged and its check passes.
	 */
	private static final String OPTION_MIN_THROUGHPUT_OVERLAP_LEFT_MBPS = "arraycopy.min.throughput.overlap.left.mbps";

	/**
	 * Option that specifies the size in bytes of each of the two working buffers. Larger buffers
	 * exceed the CPU data cache and better exercise real memory bandwidth; smaller targets can lower
	 * this to fit the available heap. When unset, {@value #DEFAULT_BUFFER_SIZE} bytes are used.
	 */
	private static final String OPTION_BUFFER_SIZE_BYTES = "arraycopy.buffer.size.bytes";

	/**
	 * Fully-qualified name of {@link #OPTION_BUFFER_SIZE_BYTES}, resolved once at class load.
	 * {@link #testArrayCopyThroughput()} looks the option up through this precomputed name with
	 * {@link System#getProperty(String)}, which does not allocate on the heap when the option is unset.
	 * This keeps the working buffers the first heap allocations in the test, so their base address
	 * stays stable and well-aligned (word-wide copy throughput is sensitive to that alignment).
	 */
	private static final String PROPERTY_BUFFER_SIZE_BYTES = PROPERTY_PREFIX + OPTION_BUFFER_SIZE_BYTES;

	private static final int DEFAULT_MIN_THROUGHPUT_MBPS = 0;

	/**
	 * Default size in bytes of each of the two working buffers (64 KB). Two buffers of this size
	 * (128 KB total) exceed a typical MCU data cache, so the copy already exercises real memory
	 * bandwidth rather than staying cache-resident; larger buffers do not change the measured
	 * throughput but need a proportionally larger Java heap.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 64 * 1024;

	/** Number of untimed warm-up copies (stabilizes the caches). */
	private static final int WARMUP_ITERATIONS = 2;

	/** Number of timed copies averaged into the throughput figure. */
	private static final int TIMED_ITERATIONS = 20;

	/** Destination offset, in bytes, used to force a relatively misaligned copy. */
	private static final int MISALIGN_OFFSET = 1;

	/**
	 * Shift, in bytes, between the source and destination regions of an overlapping copy. A one-byte
	 * shift keeps the two regions overlapping over all but one byte, so {@code memmove} must pick its
	 * copy direction from the sign of the shift.
	 */
	private static final int OVERLAP_OFFSET = 1;

	/** Number of nanoseconds in one second. */
	private static final long NS_PER_SECOND = 1_000_000_000L;

	/** Number of bytes in one megabyte, using the decimal (MB) convention. */
	private static final long BYTES_PER_MB = 1_000_000L;

	/**
	 * Measures aligned, misaligned, overlap-right and overlap-left {@link System#arraycopy} throughput
	 * and asserts each meets its configured minimum.
	 */
	@Test
	public void testArrayCopyThroughput() {
		// Resolve the buffer size WITHOUT allocating on the heap first, so the two working buffers stay
		// the first heap allocations in this method and keep a stable, well-aligned base address (the
		// aligned word-wide copy throughput is sensitive to that alignment). System.getProperty returns
		// an existing reference or null (no allocation), and Integer.parseInt returns a primitive
		// (no allocation on its success path); the resolved value is only logged after allocation.
		int bufferSize = DEFAULT_BUFFER_SIZE;
		String configuredBufferSize = System.getProperty(PROPERTY_BUFFER_SIZE_BYTES);
		if (configuredBufferSize != null) {
			bufferSize = Integer.parseInt(configuredBufferSize);
		}
		byte[] src = new byte[bufferSize];
		byte[] dst = new byte[bufferSize];

		System.out.println("Property '" + PROPERTY_BUFFER_SIZE_BYTES + "' = " + bufferSize);

		// Fill source with a non-zero pattern so the copy cannot be optimized away.
		for (int i = 0; i < bufferSize; i++) {
			src[i] = (byte) i;
		}

		// Copies between the two distinct buffers (no overlap).
		long alignedMBps = measureThroughput(src, dst, 0, 0, bufferSize);
		long misalignedMBps = measureThroughput(src, dst, 0, MISALIGN_OFFSET, bufferSize - MISALIGN_OFFSET);

		// Overlapping copies within a single buffer. Shifting the destination one byte above the source
		// (right) forces memmove to copy backwards; one byte below (left) lets it copy forwards.
		long overlapRightMBps = measureThroughput(src, src, 0, OVERLAP_OFFSET, bufferSize - OVERLAP_OFFSET);
		long overlapLeftMBps = measureThroughput(src, src, OVERLAP_OFFSET, 0, bufferSize - OVERLAP_OFFSET);

		System.out.println("System.arraycopy throughput (aligned)        : " + alignedMBps + " MB/s");
		System.out.println("System.arraycopy throughput (misaligned)     : " + misalignedMBps + " MB/s");
		System.out.println("System.arraycopy throughput (overlap right)  : " + overlapRightMBps + " MB/s");
		System.out.println("System.arraycopy throughput (overlap left)   : " + overlapLeftMBps + " MB/s");

		int minAlignedMBps = getOptionAsInt(OPTION_MIN_THROUGHPUT_MBPS, DEFAULT_MIN_THROUGHPUT_MBPS);
		int minMisalignedMBps = getOptionAsInt(OPTION_MIN_THROUGHPUT_MISALIGNED_MBPS, DEFAULT_MIN_THROUGHPUT_MBPS);
		int minOverlapRightMBps = getOptionAsInt(OPTION_MIN_THROUGHPUT_OVERLAP_RIGHT_MBPS, DEFAULT_MIN_THROUGHPUT_MBPS);
		int minOverlapLeftMBps = getOptionAsInt(OPTION_MIN_THROUGHPUT_OVERLAP_LEFT_MBPS, DEFAULT_MIN_THROUGHPUT_MBPS);

		assertTrue("Aligned System.arraycopy throughput (" + alignedMBps + " MB/s) is below the required minimum ("
				+ minAlignedMBps + " MB/s)", alignedMBps >= minAlignedMBps);
		assertTrue("Misaligned System.arraycopy throughput (" + misalignedMBps + " MB/s) is below the required minimum ("
				+ minMisalignedMBps + " MB/s)", misalignedMBps >= minMisalignedMBps);
		assertTrue("Overlap-right System.arraycopy throughput (" + overlapRightMBps
				+ " MB/s) is below the required minimum (" + minOverlapRightMBps + " MB/s)",
				overlapRightMBps >= minOverlapRightMBps);
		assertTrue("Overlap-left System.arraycopy throughput (" + overlapLeftMBps
				+ " MB/s) is below the required minimum (" + minOverlapLeftMBps + " MB/s)",
				overlapLeftMBps >= minOverlapLeftMBps);
	}

	/**
	 * Times repeated copies of the given size and returns the measured throughput.
	 *
	 * @param src
	 *            the source buffer.
	 * @param dst
	 *            the destination buffer.
	 * @param srcOffset
	 *            the offset of the first copied byte in the source buffer.
	 * @param dstOffset
	 *            the offset of the first written byte in the destination buffer.
	 * @param size
	 *            the number of bytes copied per iteration.
	 * @return the measured throughput, in MB/s (decimal megabytes per second).
	 */
	private static long measureThroughput(byte[] src, byte[] dst, int srcOffset, int dstOffset, int size) {
		for (int i = 0; i < WARMUP_ITERATIONS; i++) {
			System.arraycopy(src, srcOffset, dst, dstOffset, size);
		}

		long startNs = Util.platformTimeNanos();
		for (int i = 0; i < TIMED_ITERATIONS; i++) {
			System.arraycopy(src, srcOffset, dst, dstOffset, size);
		}
		long elapsedNs = Util.platformTimeNanos() - startNs;

		if (elapsedNs <= 0) {
			return 0;
		}
		long totalBytes = (long) size * TIMED_ITERATIONS;
		return (totalBytes * NS_PER_SECOND / elapsedNs) / BYTES_PER_MB;
	}

	/**
	 * Reads an integer option from the system properties, logging the resolved value.
	 *
	 * @param optionName
	 *            the option name, appended to {@value #PROPERTY_PREFIX}.
	 * @param defaultValue
	 *            the value returned when the property is not set or not a valid integer.
	 * @return the resolved option value.
	 */
	private static int getOptionAsInt(String optionName, int defaultValue) {
		String propertyName = PROPERTY_PREFIX + optionName;
		int value = Integer.getInteger(propertyName, defaultValue).intValue();
		System.out.println("Property '" + propertyName + "' = " + value);
		return value;
	}
}
