package com.dazz.socialqa.opennlp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Sequence;
import opennlp.tools.util.Span;

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

    /*
    public void testOpenNlpTextAnalyzer() throws Exception {
        File taeDescriptor = FileUtil.getFile("component://socialqa/uima/descriptors/opennlp/OpenNlpTextAnalyzer.xml");
        File inputFile = FileUtil.getFile("component://socialqa/webapp/socialqa/example/data/Apache_UIMA.txt");
        processTAE(taeDescriptor, inputFile);
    }
    */

    /**
     * test sentence detection
     * @throws Exception
     */
    public void testSentenceDetection() throws Exception {
        String sentenceContent = "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is"
                + " chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years"
                + " old and former chairman of Consolidated Gold Fields PLC, was named a director of this"
                + " British industrial conglomerate.";

        InputStream sentenceModelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-sent.bin"));
        SentenceModel model = new SentenceModel(sentenceModelIn);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
        String sentences[] = sentenceDetector.sentDetect("  First sentence. Second sentence. ");
        for (String sentence : sentences) {
            Debug.logInfo("Sentence: " + sentence, module);
        }
        
        Span posSentences[] = sentenceDetector.sentPosDetect("  First sentence. Second sentence. ");
        for (Span posSentence : posSentences) {
            Debug.logInfo("POS Sentence: " + posSentence, module);
        }
    }
    
    /**
     * test sentence detector training
     * @throws Exception
     */
    public void testSentenceDetectorTraining() throws Exception {
        /*
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-sent.train").getAbsolutePath()),
            charset);
        ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

        SentenceModel model = SentenceDetectorME.train("en", sampleStream, true, null, 5, 100);
        
        sampleStream.close();
        
        OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
        model.serialize(modelOut);
        modelOut.close();
        */
    }
    
    /**
     * test tokenizer
     * @throws Exception
     */
    public void testTokenizer() throws Exception {
        InputStream modelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-token.bin"));
        TokenizerModel model = new TokenizerModel(modelIn);
        modelIn.close();
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize("An input sample sentence.");
        for (String token : tokens) {
            Debug.logInfo("Token: " + token, module);
        }
        
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
        String sentenceContent = "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is"
                + " chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years"
                + " old and former chairman of Consolidated Gold Fields PLC, was named a director of this"
                + " British industrial conglomerate.";
        InputStream modelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-ner-person.bin"));
        TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
        modelIn.close();
        InputStream sentenceModelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-sent.bin"));
        SentenceModel sentenceModel = new SentenceModel(sentenceModelIn);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);
        String sentences[] = sentenceDetector.sentDetect(sentenceContent);
        NameFinderME nameFinder = new NameFinderME(model);
        Span nameSpans[] = nameFinder.find(sentences);
        for (Span nameSpan : nameSpans) {
            Debug.logInfo("Name span: " + nameSpan, module);
        }
        nameFinder.clearAdaptiveData();
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
    /*
    public void testDocumentCategorizer() throws Exception {
        String sentenceContent = "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is"
                + " chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years"
                + " old and former chairman of Consolidated Gold Fields PLC, was named a director of this"
                + " British industrial conglomerate.";
        InputStream sentenceModelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-sent.bin"));
        DoccatModel m = new DoccatModel(sentenceModelIn);
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(m);
        double[] outcomes = myCategorizer.categorize(sentenceContent);
        for (double outcome : outcomes) {
            Debug.logInfo("Outcome: " + outcome, module);
        }
        String category = myCategorizer.getBestCategory(outcomes);
        Debug.logInfo("Category: " + category, module);
    }
    */
    
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
        InputStream modelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-pos-maxent.bin"));
        POSModel model = new POSModel(modelIn);
        modelIn.close();
        POSTaggerME tagger = new POSTaggerME(model);
        String sent[] = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
                "morning", "and", "afternoon", "newspapers", "."};          
        String tags[] = tagger.tag(sent);
        for (String tag : tags) {
            Debug.logInfo("Tag: " + tag, module);
        }
        double probs[] = tagger.probs();
        for (double prob : probs) {
            Debug.logInfo("Prob: " + prob, module);
        }
        Sequence topSequences[] = tagger.topKSequences(sent);
        for (Sequence topSequence : topSequences) {
            Debug.logInfo("Sequence: " + topSequence, module);
        }
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
        InputStream modelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-chunker.bin"));
        ChunkerModel model = new ChunkerModel(modelIn);
        modelIn.close();
        ChunkerME chunker = new ChunkerME(model);
        String sent[] = new String[] { "Rockwell", "International", "Corp.", "'s",
            "Tulsa", "unit", "said", "it", "signed", "a", "tentative", "agreement",
            "extending", "its", "contract", "with", "Boeing", "Co.", "to",
            "provide", "structural", "parts", "for", "Boeing", "'s", "747",
            "jetliners", "." };

        String pos[] = new String[] { "NNP", "NNP", "NNP", "POS", "NNP", "NN",
            "VBD", "PRP", "VBD", "DT", "JJ", "NN", "VBG", "PRP$", "NN", "IN",
            "NNP", "NNP", "TO", "VB", "JJ", "NNS", "IN", "NNP", "POS", "CD", "NNS",
            "." };

        String tags[] = chunker.chunk(sent, pos);
        for (String tag : tags) {
            Debug.logInfo("Tag: " + tag, module);
        }
        double probs[] = chunker.probs();
        for (double prob : probs) {
            Debug.logInfo("Prob: " + prob, module);
        }
        Sequence topSequences[] = chunker.topKSequences(sent, pos);
        for (Sequence topSequence : topSequences) {
            Debug.logInfo("topSequence: " + topSequence, module);
        }
    }
    
    /**
     * test parser
     * @throws Exception
     */
    public void testParser() throws Exception {
        InputStream modelIn = new FileInputStream(FileUtil.getFile("component://socialqa/opennlp/models/en-parser-chunking.bin"));
        ParserModel model = new ParserModel(modelIn);
        Parser parser = ParserFactory.create(model);
        String sentence = "The quick brown fox jumps over the lazy dog .";
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        for (Parse topParse : topParses) {
            Debug.logInfo("Top parse: " + topParse, module);
        }
    }
}
