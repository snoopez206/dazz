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

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javolution.util.FastMap;

import org.ofbiz.base.util.GeneralException;
import org.ofbiz.content.content.ContentWorker;
import org.ofbiz.entity.Delegator;
import org.ofbiz.service.LocalDispatcher;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class RdfWorker {

    public static OntModel getOntModel(LocalDispatcher dispatcher, Delegator delegator, String contentId, Locale locale, String mimeTypeId, boolean cache) throws GeneralException, IOException {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        Map<String, Object> templateContext = FastMap.newInstance();
        String content = ContentWorker.renderContentAsText(dispatcher, delegator, contentId, templateContext, locale, mimeTypeId, cache);
        model.read(content);
        return model;
    }
}
