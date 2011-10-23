/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import opennlp.tools.cmdline.sentdetect.SentenceEvaluationErrorListener;
import opennlp.tools.sentdetect.SentenceDetectorEvaluationMonitor;
import opennlp.tools.sentdetect.SentenceDetectorEvaluator;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

public class SentenceDetectorTests extends OFBizTestCase {

    public final static String module = SentenceDetectorTests.class.getName();
    
    public SentenceDetectorTests(String name) {
        super(name);
    }

    /**
     * test sent detect
     * @throws Exception
     */
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
    
    /**
     * test sent position detect
     * @throws Exception
     */
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
    
    /**
     * test train
     * @throws Exception
     */
    public void testTrain() throws Exception {
        File modelFile = FileUtil.getFile("component://dazz/models/en-sent.train");
        if (!modelFile.exists()) {
            modelFile.delete();
        }
        modelFile.createNewFile();
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(modelFile), "UTF-8");
        ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

        SentenceModel model = SentenceDetectorME.train("en",sampleStream, true, null, 5, 100);
        OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
        model.serialize(modelOut);
    }
    
    /**
     * test evaluate
     * @throws Exception
     */
    public void testEvaluate() throws Exception {
        final File modelFile = FileUtil.getFile("component://dazz/models/en-sent.bin");

        InputStream in = new FileInputStream(modelFile);
        SentenceModel model = new SentenceModel(in);
        
        SentenceDetectorEvaluationMonitor errorListener = new SentenceEvaluationErrorListener();
        
        SentenceDetectorEvaluator evaluator = new SentenceDetectorEvaluator(
            new SentenceDetectorME(model), errorListener);

        Debug.logInfo("Evaluating ...", module);
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(modelFile), "UTF-8");
        ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);
        evaluator.evaluate(sampleStream);
    }
}
