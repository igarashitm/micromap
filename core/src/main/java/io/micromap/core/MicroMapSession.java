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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import io.micromap.model.Mapping;

public class MicroMapSession {

    private Map<String, JsonNode> dataSources = new HashMap<>();
    private MicroMapContext micromapContext;

    /**
     * Creates a new session.
     * @param context The mapping context.
     */
    public MicroMapSession(MicroMapContext context) {
        micromapContext = context;
    }

    /**
     * Processes data mapping.
     */
    public void process() {
        micromapContext.getMappingDefinition().getMappings().forEach(mapping -> processMapping(mapping));
    }

    private void processMapping(Mapping mapping) {
        var sourcePath = new PathExpression(mapping.getSource());
        var sourceDocument = dataSources.get(sourcePath.getDataSource());
        final List<JsonNode> outputs;
        try {
            outputs = micromapContext.getQueryHandler().readSource(sourcePath, sourceDocument);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        var targetPath = new PathExpression(mapping.getTarget());
        var targetDocument = dataSources.get(targetPath.getDataSource());
        var written = micromapContext.getQueryHandler().writeTarget(targetPath, targetDocument, outputs);
        dataSources.put(targetPath.getDataSource(), written);

        if (mapping.getMappings() != null) {
            mapping.getMappings().forEach(subMapping -> processMapping(subMapping));
        }
    }

    /**
     * Puts a data source.
     * @param key The data source key.
     * @param node The data source value.
     */
    public void putDataSource(String key, JsonNode node) {
        dataSources.put(key, node);
    }

    /**
     * Gets a data source.
     * @param key The data source key.
     * @return The data source value.
     */
    public JsonNode getDataSource(String key) {
        return dataSources.get(key);
    }

}
