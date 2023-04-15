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

public class PathExpression {

    private String dataSource;
    private String path;

    public PathExpression(String source) {
        var parts = source.split(":", 2);
        dataSource = parts[0];
        if (parts.length == 2) {
            path = parts[1];
        } else {
            throw new RuntimeException("Unexpected path expression: " + source);
        }
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getQuery() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof PathExpression) {
            var other = (PathExpression) obj;
            return dataSource.equals(other.dataSource) && path.equals(other.path);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return dataSource.hashCode() ^ path.hashCode();
    }
}
