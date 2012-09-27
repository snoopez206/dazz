/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.dazz.rdf;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class RdfTests extends OFBizTestCase {
    
    public final static String module = RdfTests.class.getName();

    private final String country = "US";
    private final String language = "en";
    private final Locale locale = new Locale(language, country);

    public RdfTests(String name) {
        super(name);
        
    }
    
    public void testQueryOntModel() throws Exception {
        String queryString = "prefix pizza:<http://www.co-ode.org/ontologies/pizza/pizza.owl#>"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "prefix owl: <http://www.w3.org/2002/07/owl#>"
                + "select ?pizza where {?pizza a owl:Class ; rdfs:subClassOf ?restriction."
                + "?restriction owl:onProperty pizza:hasTopping ; owl:someValuesFrom pizza:PeperoniSausageTopping }";
        
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        FileManager.get().readModel(model, FileUtil.getFile("component://dazz/webapp/dazz/resources/pizza.owl.rdf").getPath());
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = qexec.execSelect();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.out(out, resultSet);
        out.flush();
        String queryResult = out.toString();
        Debug.logInfo("=== Query Result: " + queryResult, module);
    }

    /*
    public void testQueryOntModelContent() throws Exception {
        String queryString = "prefix pizza:<http://www.co-ode.org/ontologies/pizza/pizza.owl#>"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "prefix owl: <http://www.w3.org/2002/07/owl#>"
                + "select ?pizza where {?pizza a owl:Class ; rdfs:subClassOf ?restriction."
                + "?restriction owl:onProperty pizza:hasTopping ; owl:someValuesFrom pizza:PeperoniSausageTopping }";
        
        OntModel model = RdfWorker.getOntModel(dispatcher, delegator, "PIZZA", locale, null, false);
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = qexec.execSelect();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.out(out, resultSet);
        out.flush();
        String queryResult = out.toString();
        Debug.logInfo("=== Query Result: " + queryResult, module);
    }
    */
}
