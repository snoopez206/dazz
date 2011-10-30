/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opennlp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

import com.dazz.opennlp.tools.postag.PartOfSpeechUtil;

public class ParserTests extends OFBizTestCase {

    public final static String module = ParserTests.class.getName();
    
    public ParserTests(String name) {
        super(name);
    }

    public void testParse() throws Exception {
        String sentence = "How many legs does a dog have?";
        ParserModel parserModel = PartOfSpeechUtil.trainParserModel();
        Parser parser = ParserFactory.create(parserModel);
        Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);
        for (Parse topParse : topParses) {
            StringBuffer buffer = new StringBuffer();
            topParse.show(buffer);
            Debug.logInfo("* top parse: " + buffer, module);
        }
    }
}
