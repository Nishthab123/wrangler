/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import java.util.List; // for List
import io.cdap.wrangler.api.parser.Token; // for Token
import io.cdap.wrangler.api.parser.TokenType; // for TokenType

 import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class BoolList implements Token {
    private final List<Boolean> values;

    public BoolList(final List<Boolean> values) {
        this.values = values;
    }

    @Override
    public List<Boolean> value() {
        return values;
    }

    @Override
    public TokenType type() {
        return TokenType.BOOLEAN_LIST;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("type", TokenType.BOOLEAN_LIST.name());

        JsonArray jsonArray = new JsonArray();
        for (Boolean value : values) {
            jsonArray.add(new JsonPrimitive(value)); // Convert Boolean to JsonElement (JsonPrimitive)
        }

        object.add("values", jsonArray);

        return object;
    }
}
