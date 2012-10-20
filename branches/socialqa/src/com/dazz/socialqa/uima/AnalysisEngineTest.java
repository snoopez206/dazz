package com.dazz.socialqa.uima;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.examples.PrintAnnotations;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

public class AnalysisEngineTest extends OFBizTestCase {

    public final static String module = AnalysisEngineTest.class.getName();

    public AnalysisEngineTest(String name) {
        super(name);

    }
    
    private void processTAE(File taeDescriptor, File inputFile) throws Exception {
        // get Resource Specifier from XML file
        XMLInputSource in = new XMLInputSource(taeDescriptor);
        ResourceSpecifier specifier = UIMAFramework.getXMLParser()
                .parseResourceSpecifier(in);

        // for debugging, output the Resource Specifier
        // System.out.println(specifier);

        // create Analysis Engine
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
        // create a CAS
        CAS cas = ae.newCAS();

        if (!inputFile.isDirectory()) {
            File aFile = inputFile;
            Debug.logInfo("Processing file " + aFile.getName(), module);

            String document = FileUtils.file2String(aFile);
            document = document.trim();

            // put document text in CAS
            cas.setDocumentText(document);

            // process
            ae.process(cas);

            // print annotations to System.out
            PrintAnnotations.printAnnotations(cas, System.out);

            // reset the CAS to prepare it for processing the next
            // document
            cas.reset();
        }
        ae.destroy();
    }

    public void testGovernmentOfficialRecognizer_RegEx_TAE() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/GovernmentOfficialRecognizer_RegEx_TAE.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/Apache_UIMA.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testNamesAndGovernmentOfficials_TAE() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/NamesAndGovernmentOfficials_TAE.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/IBM_LifeSciences.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testNamesAndPersonTitles_TAE() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/NamesAndPersonTitles_TAE.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/New_IBM_Fellows.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testPersonTitleAnnotator_WithinNamesOnly() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/PersonTitleAnnotator_WithinNamesOnly.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/SeminarChallengesInSpeechRecognition.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testPersonTitleAnnotator() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/PersonTitleAnnotator.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/TrainableInformationExtractionSystems.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testRegExAnnotator() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/RegExAnnotator.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/UIMA_Seminars.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testSimpleEmailRecognizer_RegEx_TAE() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/SimpleEmailRecognizer_RegEx_TAE.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/UIMASummerSchool2003.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testSimpleNameRecognizer_RegEx_TAE() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/SimpleNameRecognizer_RegEx_TAE.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/WatsonConferenceRooms.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testSimpleTokenAndSentenceAnnotator() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/SimpleTokenAndSentenceAnnotator.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/WatsonConferenceRooms.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testSofaExampleAnnotator() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/SofaExampleAnnotator.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/WatsonConferenceRooms.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testUIMA_Analysis_Example() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/UIMA_Analysis_Example.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/WatsonConferenceRooms.txt");
        processTAE(taeDescriptor, inputFile);
    }

    public void testXmlDetagger() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/webapp/socialqa/example/descriptors/analysis_engine/XmlDetagger.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/WatsonConferenceRooms.txt");
        processTAE(taeDescriptor, inputFile);
    }

}
