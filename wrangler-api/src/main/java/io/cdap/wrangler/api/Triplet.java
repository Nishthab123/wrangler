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

/**
 * A triplet consisting of three elements - first, second, and third.
 *
 * This class provides immutable access to the elements of the triplet.
 *
 * @param <F> type of the first element.
 * @param <S> type of the second element.
 * @param <T> type of the third element.
 */
public final class Triplet<F, S, T> {
  /** First element of the triplet. */
  private final F first;
  /** Second element of the triplet. */
  private final S second;
  /** Third element of the triplet. */
  private final T third;

  /**
   * Constructs a new Triplet.
   *
   * @param f the first element of the triplet.
   * @param s the second element of the triplet.
   * @param t the third element of the triplet.
   */
  public Triplet(final F f, final S s, final T t) {
    this.first = f;
    this.second = s;
    this.third = t;
  }

  public F getFirst() {
    return first;
  }

  public S getSecond() {
    return second;
  }

  public T getThird() {
    return third;
  }
}
