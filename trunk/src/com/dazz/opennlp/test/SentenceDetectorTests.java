/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

public class SentenceDetectorTests extends OFBizTestCase {

    public final static String module = SentenceDetectorTests.class.getName();
    
    public SentenceDetectorTests(String name) {
        super(name);
    }

    public void testSentDetect() throws Exception {
        File modelFile = FileUtil.getFile("component://dazz/models/en-sent.bin");
        InputStream in = new FileInputStream(modelFile);
        SentenceModel model = new SentenceModel(in);
        SentenceDetectorME detector = new SentenceDetectorME(model);
        String rawText = "  First sentence. Second sentence. ";
        Debug.logInfo("raw text: " + "\"" + rawText + "\"", module);
        String[] sentences = detector.sentDetect(rawText);
        for (String sentence : sentences) {
            Debug.logInfo("sentence: \"" + sentence + "\"", module);
        }
    }
    
    public void testSentPosDetect() throws Exception {
        File modelFile = FileUtil.getFile("component://dazz/models/en-sent.bin");
        InputStream in = new FileInputStream(modelFile);
        SentenceModel model = new SentenceModel(in);
        SentenceDetectorME detector = new SentenceDetectorME(model);
        String rawText = "  First sentence. Second sentence. ";
        Debug.logInfo("raw text: " + "\"" + rawText + "\"", module);
        Span[] sentences = detector.sentPosDetect(rawText);
        for (Span sentence : sentences) {
            Debug.logInfo("sentence: \"" + sentence + "\"", module);
        }
    }
}
