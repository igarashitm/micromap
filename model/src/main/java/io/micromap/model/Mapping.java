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

/**
 * A model represents a single mapping entry.
 */
public class Mapping implements Serializable {
    @JsonProperty("Mappings")
    private List<Mapping> mappings = Collections.emptyList();
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Target")
    private String target;

    /**
     * Gets the list of mappings.
     * @return A list of Mappings.
     */
    public List<Mapping> getMappings() {
        return Collections.unmodifiableList(mappings);
    }

    /**
     * Gets the source path expression.
     * @return The source path expression.
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the target path expression.
     * @return The target path expression.
     */
    public String getTarget() {
        return target;
    }

}
