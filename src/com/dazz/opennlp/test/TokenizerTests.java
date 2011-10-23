/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

public class TokenizerTests extends OFBizTestCase {

    public final static String module = TokenizerTests.class.getName();
    
    public TokenizerTests(String name) {
        super(name);
    }

    /**
     * test tokenize
     * @throws Exception
     */
    public void testTokenize() throws Exception {
        File modelFile = FileUtil.getFile("component://dazz/models/en-token.bin");
        InputStream in = new FileInputStream(modelFile);
        TokenizerModel model = new TokenizerModel(in);
        Tokenizer tokenizer = new TokenizerME(model);
        String rawText = "An input sample sentence.";
        Debug.logInfo("raw text: \"" + rawText + "\"", module);
        String[] tokens = tokenizer.tokenize(rawText);
        for (String token : tokens) {
            Debug.logInfo("token: \"" + token + "\"", module);
        }
    }
    
    /**
     * test tokenize position
     * @throws Exception
     */
    public void testTokenizePos() throws Exception {
        File modelFile = FileUtil.getFile("component://dazz/models/en-token.bin");
        InputStream in = new FileInputStream(modelFile);
        TokenizerModel model = new TokenizerModel(in);
        Tokenizer tokenizer = new TokenizerME(model);
        String rawText = "An input sample sentence.";
        Debug.logInfo("raw text: \"" + rawText + "\"", module);
        Span[] tokens = tokenizer.tokenizePos(rawText);
        for (Span token : tokens) {
            Debug.logInfo("token: \"" + token + "\"", module);
        }
    }
}
