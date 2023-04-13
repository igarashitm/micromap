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
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import io.micromap.model.MappingDefinition;

public class MicroMapSession {

    private Map<String, JsonNode> dataSources = new HashMap<>();
    private MappingDefinition mappingDefinition;

    /**
     * Creates a new session.
     * @param def The mapping definition.
     */
    public MicroMapSession(MappingDefinition def) {
        mappingDefinition = def;
    }

    /**
     * Processes data mapping.
     */
    public void process() {

    }

    /**
     * Adds a data source.
     * @param key The data source key.
     * @param node The data source value.
     */
    public void addDataSource(String key, JsonNode node) {
        dataSources.put(key, node);
    }


}
