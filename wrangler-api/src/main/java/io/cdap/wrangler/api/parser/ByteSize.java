/*
 *  Copyright © 2017-2019 Cask Data, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.cdap.wrangler.api.parser;

public class ByteSize {

    private static final int BYTE_SIZE = 1024; // Magic Number replaced with a constant
    private static final int KILOBYTE_SIZE = BYTE_SIZE * BYTE_SIZE;
    private static final int MEGABYTE_SIZE = KILOBYTE_SIZE * BYTE_SIZE;
    private static final int GIGABYTE_SIZE = MEGABYTE_SIZE * BYTE_SIZE;
    private static final int TERABYTE_SIZE = GIGABYTE_SIZE * BYTE_SIZE;

    private final long value; // Final parameter for immutability

    // Constructor for ByteSize
    public ByteSize(final long value) {
        this.value = value;
    }

    public final long getBytes() {
        return this.value;
    }

    // Method to convert the byte size to a human-readable format
    public final String toJson() {
        if (value < KILOBYTE_SIZE) {
            return value + " Bytes";
        } else if (value < MEGABYTE_SIZE) {
            return (value / KILOBYTE_SIZE) + " KB";
        } else if (value < GIGABYTE_SIZE) {
            return (value / MEGABYTE_SIZE) + " MB";
        } else if (value < TERABYTE_SIZE) {
            return (value / GIGABYTE_SIZE) + " GB";
        } else {
            return (value / TERABYTE_SIZE) + " TB";
        }
    }

    public String type() {
        if (value < KILOBYTE_SIZE) {
            return "Byte";
        } else if (value < MEGABYTE_SIZE) {
            return "KB";
        } else if (value < GIGABYTE_SIZE) {
            return "MB";
        } else if (value < TERABYTE_SIZE) {
            return "GB";
        } else {
            return "TB";
        }
    }

    // Regular expressions for parsing sizes
    public static final String BYTE_SIZE_REGEX = "\\d+(\\.[0-9]+)?[KMG]B?"; // Adjusted regex for single-line
    public static final String BYTES_PATTERN = "\\d+"; // Adjusted regex for single-line

    // Parsing methods (adjusted for readability)
    public static ByteSize fromString(final String size) {
        final String trimmedSize = size.trim(); // Remove any extra spaces
        if (trimmedSize.matches(BYTE_SIZE_REGEX)) {
            long value = Long.parseLong(trimmedSize.replaceAll("[^0-9]", ""));
            if (trimmedSize.contains("KB")) {
                value *= KILOBYTE_SIZE;
            } else if (trimmedSize.contains("MB")) {
                value *= MEGABYTE_SIZE;
            } else if (trimmedSize.contains("GB")) {
                value *= GIGABYTE_SIZE;
            } else if (trimmedSize.contains("TB")) {
                value *= TERABYTE_SIZE;
            }
            return new ByteSize(value);
        }
        return null; // Return null if invalid format
    }

    public static ByteSize fromBytes(final long bytes) {
        return new ByteSize(bytes);
    }

    // Method for getting the size in a specific type
    public long getSizeIn(final String type) {
        switch (type) {
            case "KB":
                return value / KILOBYTE_SIZE;
            case "MB":
                return value / MEGABYTE_SIZE;
            case "GB":
                return value / GIGABYTE_SIZE;
            case "TB":
                return value / TERABYTE_SIZE;
            default:
                return value; // Default to bytes
        }
    }

    // Make sure the value method is not intended for extension (final method)
    public final long value() {
        return this.value;
    }

    // Additional methods for extensions as needed
}
