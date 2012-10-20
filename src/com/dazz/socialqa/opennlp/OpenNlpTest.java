package com.dazz.socialqa.opennlp;

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

/**
 * OpenNLP Test
 * @author chatree
 *
 */
public class OpenNlpTest extends OFBizTestCase {

    public final static String module = OpenNlpTest.class.getName();

    public OpenNlpTest(String name) {
        super(name);
        
        // Install Pear
        // http://mail-archives.apache.org/mod_mbox/opennlp-users/201101.mbox/%3CAANLkTinw_B4hP5XJbNj25b46k93XwR+Atk_9a1dqL-KK@mail.gmail.com%3E
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

    public void testChunker() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/uima/descriptors/opennlp/Chunker.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/Apache_UIMA.txt");
        processTAE(taeDescriptor, inputFile);
    }
}
