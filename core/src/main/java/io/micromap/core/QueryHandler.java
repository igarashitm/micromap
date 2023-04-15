/*
 * Copyright (C) 2023 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micromap.core;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.micromap.model.Json;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Version;
import net.thisptr.jackson.jq.Versions;

public class QueryHandler {
    private static final Version JQ_VERSION = Versions.JQ_1_6;

    private QueryCache queryCache = new QueryCache();

    public List<JsonNode> readSource(PathExpression sourcePath, JsonNode sourceDocument) throws Exception {
        JsonQuery sourceQuery = getQuery(sourcePath);
        final List<JsonNode> outputs = new LinkedList<>();
        sourceQuery.apply(Scope.newEmptyScope(), sourceDocument, outputs::add);
        return outputs;
    }

    private JsonQuery getQuery(PathExpression expression) throws Exception {
        var query = queryCache.get(expression);
        if (query != null) {
            return query;
        }
        query = JsonQuery.compile(expression.getQuery(), JQ_VERSION);
        queryCache.put(expression, query);
        return query;
    }

    public JsonNode writeTarget(PathExpression targetPath, JsonNode targetDocument, List<JsonNode> sources) {
        var answer = targetDocument;
        if (answer == null) {
            answer = Json.mapper().createObjectNode();
        }
        var parsedPath = new TargetPath(targetPath.getQuery());
        // TODO implement
        return answer;
    }


}
