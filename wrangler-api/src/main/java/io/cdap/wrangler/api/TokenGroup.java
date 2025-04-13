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
package io.cdap.wrangler.api;

import io.cdap.wrangler.api.parser.Token;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a group of tokens.
 */
public class TokenGroup implements Iterable<Token> {

  /** List to store token information as Token objects. */
  private List<Token> tokenInfos = new ArrayList<>();

  /** List to store token values. */
  private List<String> tokenValues = new ArrayList<>();

  /** Default constructor */
  public TokenGroup() {
    // Default constructor.
  }

  /**
   * Constructor that accepts a SourceInfo and initializes the token group.
   * @param sourceInfo the SourceInfo to initialize the group.
   */
  public TokenGroup(SourceInfo sourceInfo) {
    // Use getSource() for info and concatenate line/column for value
    this.tokenInfos.add(new Token(sourceInfo.getSource(), Token.TokenType.WORD)); // Example Token creation
    this.tokenValues.add(String.format("Line: %d, Column: %d", sourceInfo.getLineNumber(), sourceInfo.getColumnNumber()));
  }

  /**
   * Adds a token to the group.
   * @param token the Token object to add
   */
  public final void add(Token token) {
    this.tokenInfos.add(token);
  }

  /**
   * Returns an iterator for the token information (tokenInfos list).
   * @return the iterator for tokenInfos
   */
  @Override
  public Iterator<Token> iterator() {
    return tokenInfos.iterator();
  }

  // Optional getter methods to access the stored tokens
  public List<Token> getTokenInfos() {
    return new ArrayList<>(tokenInfos); // Return a copy to prevent modification
  }

  public List<String> getTokenValues() {
    return new ArrayList<>(tokenValues); // Return a copy to prevent modification
  }
}
