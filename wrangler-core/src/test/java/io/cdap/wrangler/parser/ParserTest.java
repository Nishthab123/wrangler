/*
 * Copyright 2025 <Your Organization or Name>
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

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testByteSizeParsing() {
        ByteSize byteSize = new ByteSize("10KB");
        assertEquals(10240, byteSize.getBytes());

        byteSize = new ByteSize("2.5MB");
        assertEquals(2621440, byteSize.getBytes());
    }

    @Test
    public void testTimeDurationParsing() {
        TimeDuration timeDuration = new TimeDuration("100ms");
        assertEquals(100, timeDuration.getMilliseconds());

        timeDuration = new TimeDuration("1.5min");
        assertEquals(90000, timeDuration.getMilliseconds());
    }
}

