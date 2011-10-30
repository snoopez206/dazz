/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

import org.ofbiz.base.util.Debug;
import org.ofbiz.service.testtools.OFBizTestCase;

import com.dazz.opennlp.tools.postag.PartOfSpeechUtil;

public class ChunkerTests extends OFBizTestCase {
    
    public final static String module = ChunkerTests.class.getName();

    public ChunkerTests(String name) {
        super(name);
    }

    public void testChunk() throws Exception {
        String[] tokens = new String[] {
                "Forecasts",
                "for",
                "the",
                "trade",
                "figures",
                "range",
                "widely",
                ",",
                "Forecasts",
                "for",
                "the",
                "trade",
                "figures",
                "range",
                "widely",
                "."};
        String[] tags = new String[] {
                "NNS",
                "IN",
                "DT",
                "NN",
                "NNS",
                "VBP",
                "RB",
                ",",
                "NNS",
                "IN",
                "DT",
                "NN",
                "NNS",
                "VBP",
                "RB",
                "."};
        ChunkerModel chunkerModel = PartOfSpeechUtil.trainChunkerModel();
        ChunkerME chunker = new ChunkerME(chunkerModel);
        String[] preds = chunker.chunk(tokens, tags);
        for (int i = 0; i < preds.length; i ++) {
            Debug.logInfo("* pred: " + preds[i], module);
        }
    }
}
