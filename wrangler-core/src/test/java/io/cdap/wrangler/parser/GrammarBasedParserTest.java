/*
 *  Copyright © 2017-2019 Cask Data, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */

package io.cdap.wrangler.parser;

import io.cdap.wrangler.TestingRig;
import io.cdap.wrangler.api.CompileStatus;
import io.cdap.wrangler.api.Compiler;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.RecipeParser;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests {@link GrammarBasedParser}
 */
public class GrammarBasedParserTest {

  @Test
  public void testBasic() throws Exception {
    String[] recipe = new String[] {
      "#pragma version 2.0;",
      "rename :col1 :col2",
      "parse-as-csv :body ',' true;",
      "#pragma load-directives text-reverse, text-exchange;",
      "${macro} ${macro_2}",
      "${macro_${test}}"
    };

    RecipeParser parser = TestingRig.parse(recipe);
    List<Directive> directives = parser.parse();
    Assert.assertEquals(2, directives.size());
  }

  @Test
  public void testLoadableDirectives() throws Exception {
    String[] recipe = new String[] {
      "#pragma version 2.0;",
      "#pragma load-directives text-reverse, text-exchange;",
      "rename col1 col2",
      "parse-as-csv body , true",
      "text-reverse :body;",
      "test prop: { a='b', b=1.0, c=true};",
      "#pragma load-directives test-change,text-exchange, test1,test2,test3,test4;"
    };

    Compiler compiler = new RecipeCompiler();
    CompileStatus status = compiler.compile(new MigrateToV2(recipe).migrate());
    Assert.assertEquals(7, status.getSymbols().getLoadableDirectives().size());
  }

  @Test
  public void testCommentOnlyRecipe() throws Exception {
    String[] recipe = new String[] {
      "// test"
    };

    RecipeParser parser = TestingRig.parse(recipe);
    List<Directive> directives = parser.parse();
    Assert.assertEquals(0, directives.size());
  }

  @Test
public void testAggregateStatsDirective() {
    // Sample recipe: aggregate-stats :data_size :duration :total_mb :total_sec
    String[] recipe = new String[] {
        "aggregate-stats :data_size :duration :total_mb :total_sec"
    };

    // Sample input rows
    List<Row> input = new ArrayList<>();
    input.add(new Row("data_size", "10MB").add("duration", "1.5s"));
    input.add(new Row("data_size", "500KB").add("duration", "2s"));

    // Execute the recipe
    List<Row> results = TestingRig.execute(recipe, input);

    // Validate results
    assertEquals(1, results.size());
    assertEquals(10.5, results.get(0).getValue("total_mb"), 0.001);
    assertEquals(3.5, results.get(0).getValue("total_sec"), 0.001);
}


}
