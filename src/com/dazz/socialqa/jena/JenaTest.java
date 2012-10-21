package com.dazz.socialqa.jena;

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
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class JenaTest extends OFBizTestCase {

    public final static String module = JenaTest.class.getName();

    public JenaTest(String name) {
        super(name);
        
    }

    protected String getEnglishLabel( Resource cheese ) {
        StmtIterator i = cheese.listProperties( RDFS.label );
        while (i.hasNext()) {
            Literal l = i.next().getLiteral();

            if (l.getLanguage() != null && l.getLanguage().equals( "en")) {
                // found the English language label
                return l.getLexicalForm();
            }
        }

        return "A Cheese with No Name!";
    }

    protected String getValueAsString( Resource r, Property p ) {
        Statement s = r.getProperty( p );
        if (s == null) {
            return "";
        }
        else {
            return s.getObject().isResource() ? s.getResource().getURI() : s.getString();
        }
    }
    
    public void testCheese() throws Exception {
        // creates a new, empty in-memory model
        Model m = ModelFactory.createDefaultModel();
        
        // load some data into the model
        FileManager.get().readModel( m, FileUtil.getFile("component://socialqa/webapp/socialqa/jena/data/cheeses-0.1.ttl").getAbsolutePath());
        
        Debug.logInfo(String.format("The model contains %d triples", m.size()), module);
        
        Resource cheeseClass = m.getResource("http://data.kasabi.com/dataset/cheese/schema/Cheese");

        StmtIterator i = m.listStatements(null, RDF.type, cheeseClass);

        while (i.hasNext()) {
            Resource cheese = i.next().getSubject();
            String label = getEnglishLabel( cheese );
            Debug.logInfo( String.format( "Cheese %s has name: %s", cheese.getURI(), label), module);
        }
    }
    
    public void testPizzaSparqlNoInf() throws Exception {
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        FileManager.get().readModel( m, FileUtil.getFile("component://socialqa/webapp/socialqa/jena/data/pizza.owl.rdf").getAbsolutePath());
        String prefix = "prefix pizza: <http://www.co-ode.org/ontologies/pizza/pizza.owl#>\n" +
                "prefix rdfs: <" + RDFS.getURI() + ">\n" +
                "prefix owl: <" + OWL.getURI() + ">\n";
        String queryString = prefix +
                "select ?pizza where {?pizza a owl:Class ; " +
                "                            rdfs:subClassOf ?restriction.\n" +
                "                     ?restriction owl:onProperty pizza:hasTopping ;" +
                "                            owl:someValuesFrom pizza:PeperoniSausageTopping" +
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out(results, m);
        }
        finally {
            qexec.close();
        }
    }
}
