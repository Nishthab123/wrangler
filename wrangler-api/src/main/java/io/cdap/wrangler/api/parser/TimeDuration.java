/*
 * Copyright © 2025 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cdap.wrangler.api.parser.Token;

 import io.cdap.wrangler.api.parser.TokenType;

 
/**
 * Represents a time duration value with parsing capabilities.
 */
 
 /*
  * TimeDuration class implementing Token interface
  */
 public class TimeDuration implements Token {
     private long milliseconds;
     private String value;
 
     public TimeDuration(String value) {
         if (value == null || value.trim().isEmpty()) {
             throw new IllegalArgumentException("Time duration cannot be null or empty.");
         }
         this.value = value.trim();
         parse(this.value);
     }
 
     private void parse(String value) {
         // Regex allows optional spaces between number and unit
         Pattern pattern = Pattern.compile("([0-9.]+)\\s*([a-zA-Z]+)");
         Matcher matcher = pattern.matcher(value);
         
         if (matcher.matches()) {
             double number = Double.parseDouble(matcher.group(1));
             String unit = matcher.group(2).toLowerCase();
 
             switch (unit) {
                 case "ms":
                 case "millisecond":
                 case "milliseconds":
                     milliseconds = (long) number;
                     break;
 
                 case "s":
                 case "sec":
                 case "secs":
                 case "second":
                 case "seconds":
                     milliseconds = (long) (number * 1000);
                     break;
 
                 case "m":
                 case "min":
                 case "mins":
                 case "minute":
                 case "minutes":
                     milliseconds = (long) (number * 60 * 1000);
                     break;
 
                 case "h":
                 case "hr":
                 case "hrs":
                 case "hour":
                 case "hours":
                     milliseconds = (long) (number * 60 * 60 * 1000);
                     break;
 
                 default:
                     throw new IllegalArgumentException("Unsupported unit: " + unit);
             }
         } else {
             throw new IllegalArgumentException("Invalid time duration format: " + value +
                     ". Expected format: [number][unit] (e.g., 10s, 5 min, 1hr).");
         }
     }
 
     public long getMilliseconds() {
         return milliseconds;
     }
 
     @Override
     public Object value() {
         return this.value;
     }
 
     @Override
     public TokenType type() {
         return TokenType.TIME_DURATION;
     }
 
     @Override
     public JsonElement toJson() {
         JsonObject json = new JsonObject();
         json.addProperty("value", value);
         json.addProperty("milliseconds", milliseconds);
         return json;
     }
 }

 