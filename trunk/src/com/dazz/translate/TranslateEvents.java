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
package com.dazz.translate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javolution.util.FastList;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.base.util.UtilHttp;

import com.dazz.opennlp.tools.postag.PartOfSpeechUtil;

public class TranslateEvents {

    public final static String module = TranslateEvents.class.getName();

    public static String process(HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> parameters = UtilHttp.getParameterMap(request);
        String question = (String) parameters.get("question");

        try {
            // sentence detector
            File sentModelFile = FileUtil
                    .getFile("component://dazz/models/en-sent.bin");
            InputStream sentIn = new FileInputStream(sentModelFile);
            SentenceModel sentModel = new SentenceModel(sentIn);
            SentenceDetectorME detector = new SentenceDetectorME(sentModel);

            // tokenizer
            File tokenModelFile = FileUtil
                    .getFile("component://dazz/models/en-token.bin");
            InputStream tokenIn = new FileInputStream(tokenModelFile);
            TokenizerModel tokenModel = new TokenizerModel(tokenIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);

            // part of speech
            POSModel posModel = PartOfSpeechUtil.trainPOSModel();
            POSTagger tagger = new POSTaggerME(posModel);

            List<StringBuffer> topParseBuffers = FastList.newInstance();
            String[] sentences = detector.sentDetect(question);
            for (String sentence : sentences) {
                Debug.logInfo("* sentence: " + sentence, module);
                String[] tokens = tokenizer.tokenize(sentence);
                String tags[] = tagger.tag(tokens);
                
                ChunkerModel chunkerModel = PartOfSpeechUtil.trainChunkerModel();
                ChunkerME chunker = new ChunkerME(chunkerModel);
                String[] preds = chunker.chunk(tokens, tags);

                for (int i = 0; i < tokens.length; i++) {
                    Debug.logInfo("* " + tokens[i] + " : " + tags[i] + " : " + preds[i], module);
                }
                
                // parse
                ParserModel parserModel = PartOfSpeechUtil.trainParserModel();
                Parser parser = ParserFactory.create(parserModel);
                Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);
                for (Parse topParse : topParses) {
                    StringBuffer buffer = new StringBuffer();
                    topParse.show(buffer);
                    Debug.logInfo("* top parse: " + buffer, module);
                    topParseBuffers.add(buffer);
                }
                request.setAttribute("topParseBuffers", topParseBuffers);
            }
            return "success";
        } catch (Exception e) {
            Debug.logError(e, module);
            return "error";
        }
    }
}
