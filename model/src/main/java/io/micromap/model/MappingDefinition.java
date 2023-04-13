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
package io.micromap.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * A model represents a mapping definition JSON file.
 */
@JsonRootName("MappingDefinition")
public class MappingDefinition implements Serializable {
    @JsonProperty("Mappings")
    private List<Mapping> mappings;

    /**
     * Gets the list of mappings.
     * @return A list of mappings.
     */
    public List<Mapping> getMappings() {
        return Collections.unmodifiableList(mappings);
    }
}
