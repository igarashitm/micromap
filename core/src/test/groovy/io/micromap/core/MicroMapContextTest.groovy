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

import io.micromap.model.Json
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
                     "Source": "camel.body:.Person.firstName + .Person.lastName",
                     "Target": "camel.body:.Person.name"
                   }
                 ]
               }
             ]
           }
           """

        when:
        def context = new MicroMapContext(mappings)
        def session = context.createSession()
        def mapper = Json.mapper();
        session.putDataSource("camel.body", null)
        session.putDataSource("camel.header.contact", mapper.readTree("""
            {
              "Contact": {
                "Address": "314 Littleton Rd, Westford, Massachusetts 01886, USA",
                 "Phone": "123-456-7890"
               }
             }
             """))
        session.putDataSource("camel.header.person", mapper.readTree("""
            {
              "Person": {
                "firstName": "John",
                 "lastName": "Doe"
               }
             }
             """))
        session.process()

        then:
        def result = session.getDataSource("camel.body")
        // TODO implement
        //result.get("Contact") != null
        //result.get("Person") != null
    }
}
