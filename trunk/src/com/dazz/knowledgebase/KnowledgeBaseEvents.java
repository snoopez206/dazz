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
package com.dazz.knowledgebase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.base.util.UtilValidate;

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

public class KnowledgeBaseEvents {

    public final static String module = KnowledgeBaseEvents.class.getName();
    
    public static String query(HttpServletRequest request, HttpServletResponse response) {
        String queryString = request.getParameter("queryString");
        if (UtilValidate.isNotEmpty(queryString)) {
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            FileManager.get().readModel(model, FileUtil.getFile("component://dazz/webapp/dazz/resources/pizza.owl.rdf").getPath());
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            try {
                ResultSet resultSet = qexec.execSelect();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ResultSetFormatter.out(out, resultSet);
                out.flush();
                String queryResult = out.toString();
                request.setAttribute("queryResult", queryResult);
            } catch (IOException e) {
                Debug.logError(e, module);
            } finally {
                qexec.close();
            }
        }
        return "success";
    }
}
