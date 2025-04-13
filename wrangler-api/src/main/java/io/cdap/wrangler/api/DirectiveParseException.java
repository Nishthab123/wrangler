/*
 * Copyright 2025 [Your Organization or Your Name]
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

 package io.cdap.wrangler.api;

 /**
  * Exception thrown when parsing of a directive fails.
  */
 public class DirectiveParseException extends Exception {
 
   /**
    * Constructs a new DirectiveParseException with the specified detail message and cause.
    *
    * @param message the detail message
    * @param e the cause
    */
   public DirectiveParseException(final String message, final Throwable e) {
     super(message, e);
   }
 
   /**
    * Constructs a new DirectiveParseException with the specified cause.
    *
    * @param e the cause
    */
   public DirectiveParseException(final Throwable e) {
     super(e);
   }
 
   /**
    * Constructs a new DirectiveParseException with the specified detail message.
    *
    * @param message the detail message
    */
   public DirectiveParseException(final String message) {
     super(message);
   }
 }
 