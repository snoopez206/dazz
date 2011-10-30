/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import opennlp.tools.postag.POSDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;

import org.ofbiz.base.util.Debug;
import org.ofbiz.service.testtools.OFBizTestCase;

import com.dazz.opennlp.tools.postag.PartOfSpeechUtil;

public class PartOfSpeechTaggerTests extends OFBizTestCase {

    public final static String module = PartOfSpeechTaggerTests.class.getName();
    
    protected POSDictionary dictionary = null;
    
    public PartOfSpeechTaggerTests(String name) {
        super(name);
    }
    
    public void testTag() throws Exception {
        POSModel posModel = PartOfSpeechUtil.trainPOSModel();
        POSTagger tagger = new POSTaggerME(posModel);
        String[] tokens = new String[] { "How", "many", "legs", "does", "a", "dog", "have" };
        String[] tags = tagger.tag(tokens);
        for (int i = 0; i < tags.length; i ++) {
            Debug.logInfo("* tag: " + tags[i], module);
        }
    }
}
