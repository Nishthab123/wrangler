/*
 * Copyright [year] [name of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// wrangler.js

// Function to parse byte sizes (KB, MB, GB, etc.)
function parseByteSize(size) {
    const units = {
        B: 1,
        KB: 1024,
        MB: 1024 * 1024,
        GB: 1024 * 1024 * 1024,
        TB: 1024 * 1024 * 1024 * 1024
    };

    const regex = /(\d+)([KMGT]?B)/i; // Regex to match sizes like '10KB', '5MB', etc.
    const match = size.match(regex);

    if (!match) {
        throw new Error('Invalid byte size format');
    }

    const value = parseInt(match[1]);
    const unit = match[2].toUpperCase(); // Normalize unit to uppercase

    return value * units[unit]; // Convert to bytes
}

// Function to parse time durations (s, m, h, d)
function parseTimeDuration(duration) {
    const units = {
        s: 1,         // seconds
        m: 60,        // minutes to seconds
        h: 3600,      // hours to seconds
        d: 86400      // days to seconds
    };

    const regex = /(\d+)([smhd])/i;  // Regex to match durations like '5s', '1h', '30m'
    const match = duration.match(regex);

    if (!match) {
        throw new Error('Invalid time duration format');
    }

    const value = parseInt(match[1]);
    const unit = match[2].toLowerCase(); // Normalize unit to lowercase

    return value * units[unit]; // Convert to seconds
}

// New aggregation function for byte sizes
function aggregateByteSizes(byteSizes) {
    return byteSizes.reduce((total, size) => total + parseByteSize(size), 0);
}

// New aggregation function for time durations
function aggregateTimeDurations(durations) {
    return durations.reduce((total, duration) => total + parseTimeDuration(duration), 0);
}

// Export functions for use in other files
module.exports = { parseByteSize, parseTimeDuration, aggregateByteSizes, aggregateTimeDurations };

