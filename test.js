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

// Test the functions

const { parseByteSize, parseTimeDuration, aggregateByteSizes, aggregateTimeDurations } = require('./wrangler.js'); // Adjust the path if necessary

// Test for parseByteSize
console.log(parseByteSize('10KB'));  // Should output: 10240 bytes
console.log(parseByteSize('2KB'));   // Should output: 2048 bytes
console.log(parseByteSize('5MB'));   // Should output: 5242880 bytes

// Test for parseTimeDuration
console.log(parseTimeDuration('5s'));  // Should output: 5 seconds
console.log(parseTimeDuration('1h'));  // Should output: 3600 seconds (1 hour)
console.log(parseTimeDuration('30m')); // Should output: 1800 seconds (30 minutes)

// Test for aggregateByteSizes
console.log(aggregateByteSizes(['10MB', '5MB']));  // Should output: 15728640 bytes (10MB + 5MB)
console.log(aggregateByteSizes(['1KB', '2KB', '3KB']));  // Should output: 6144 bytes (1KB + 2KB + 3KB)

// Test for aggregateTimeDurations
console.log(aggregateTimeDurations(['10s', '20s']));  // Should output: 30 seconds (10s + 20s)
console.log(aggregateTimeDurations(['1h', '30m']));  // Should output: 5400 seconds (1h + 30m)
