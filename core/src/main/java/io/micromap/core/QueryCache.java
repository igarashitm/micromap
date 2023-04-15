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

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import net.thisptr.jackson.jq.JsonQuery;

public class QueryCache {
    private static final long serialVersionUID = 1L;
    private Map<PathExpression, SoftReference<JsonQuery>> delegate = new HashMap<>();

    public JsonQuery get(PathExpression key) {
        var ref = delegate.get(key);
        return ref == null ? null : ref.get();
    }

    public void put(PathExpression key, JsonQuery value) {
        delegate.put(key, new SoftReference<>(value));
    }
}
