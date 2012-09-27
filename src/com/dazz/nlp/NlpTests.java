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
package com.dazz.nlp;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import org.ofbiz.service.testtools.OFBizTestCase;

public class NlpTests extends OFBizTestCase {

    public NlpTests(String name) {
        super(name);
        
    }

    /**
     * test sentence detection
     * @throws Exception
     */
    public void testSentenceDetection() throws Exception {
        String sentenceContent = "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is"
                + " chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years"
                + " old and former chairman of Consolidated Gold Fields PLC, was named a director of this"
                + " British industrial conglomerate.";
        
        SentenceModel model = new SentenceModel(in);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
        String sentences[] = sentenceDetector.sentDetect("  First sentence. Second sentence. ");
    }
    
    /**
     * test sentence detector training
     * @throws Exception
     */
    public void testSentenceDetectorTraining() throws Exception {
        Charset charset = Charset.forName("UTF-8");                
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream("en-sent.train"),
            charset);
        ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

        SentenceModel model = SentenceDetectorME.train("en", sampleStream, true, null, 5, 100);
        OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
        model.serialize(modelOut);
    }
    
    /**
     * test tokenizer
     * @throws Exception
     */
    public void testTokenizer() throws Exception {
        
    }
    
    /**
     * test tokenizer training
     * @throws Exception
     */
    public void testTokenizerTraining() throws Exception {
        
    }
    
    /**
     * test name finder
     * @throws Exception
     */
    public void testNameFinder() throws Exception {
        
    }
    
    /**
     * test name finder training
     */
    public void testNameFinderTraining() throws Exception {
        
    }
    
    /**
     * test document categorizer
     * @throws Exception
     */
    public void testDocumentCategorizer() throws Exception {
        
    }
    
    /**
     * test document categorizer training
     * @throws Exception
     */
    public void testDocumentCategorizerTraining() throws Exception {
        
    }
    
    /**
     * test POS tagger
     * @throws Exception
     */
    public void testPosTagger() throws Exception {
        
    }
    
    /**
     * test POS tagger training
     * @throws Exception
     */
    public void testPosTaggerTraining() throws Exception {
        
    }
    
    /**
     * test chunker
     * @throws Exception
     */
    public void testChunker() throws Exception {
        
    }
    
    /**
     * test parser
     * @throws Exception
     */
    public void testParser() throws Exception {
        
    }
}
