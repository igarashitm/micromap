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

import io.micromap.model.Json;
import io.micromap.model.MappingDefinition;

public class MicroMapContext {

    private MappingDefinition mappingDefinition;
    private QueryHandler queryHandler = new QueryHandler();

    public MicroMapContext(String mappings) throws Exception {
        mappingDefinition = Json.mapper().readValue(mappings, MappingDefinition.class);
    }

    public MicroMapSession createSession() {
        return new MicroMapSession(this);
    }

    public MappingDefinition getMappingDefinition() {
        return mappingDefinition;
    }

    public QueryHandler getQueryHandler() {
        return queryHandler;
    }

}
