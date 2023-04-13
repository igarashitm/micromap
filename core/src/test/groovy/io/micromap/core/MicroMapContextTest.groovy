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
package io.micromap.core

import io.micromap.model.Mapping
import io.micromap.model.MappingDefinition
import spock.lang.Specification

class MicroMapContextTest extends Specification {

    def "test"() {
        given:
        def mappings = """
            {
              "Mappings": [
                {
                  "Source": "camel.header.contact:.Contact",
                  "Target": "camel.body:.Contact"
               },
               {
                 "Source": "camel.header.person:.Person",
                 "Target": "camel.body:.Person",
                 "Mappings": [
                   {
                     "Source": "camel.body:{.Person.firstName + .Person.lastName}",
                     "Target": "camel.body:.Person.name"
                   }
                 ]
               }
             ]
           }
           """

        when:
        def context = new MicroMapContext(mappings)

        then:
        context != null
        context.getMappingDefinition() instanceof MappingDefinition
        def session = context.createSession()
        session.process()
    }
}
