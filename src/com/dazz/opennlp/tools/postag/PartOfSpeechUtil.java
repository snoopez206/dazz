package com.dazz.opennlp.tools.postag;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ofbiz.base.util.FileUtil;

import opennlp.tools.chunker.ChunkSample;
import opennlp.tools.chunker.ChunkSampleStream;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParseSampleStream;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.lang.en.HeadRules;
import opennlp.tools.parser.treeinsert.Parser;
import opennlp.tools.postag.POSDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.model.ModelType;

public class PartOfSpeechUtil {

    public final static String module = PartOfSpeechUtil.class.getName();

    public static POSDictionary loadDictionary(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        return POSDictionary.create(in);
    }

    public static POSDictionary serializeDeserializeDict(POSDictionary dict)
            throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            dict.serialize(out);
        } finally {
            out.close();
        }

        InputStream in = new ByteArrayInputStream(out.toByteArray());

        POSDictionary serializedDictionary = null;
        try {
            serializedDictionary = POSDictionary.create(in);
        } finally {
            in.close();
        }

        return serializedDictionary;
    }

    public static POSModel trainPOSModel() throws IOException {
        File modelFile = FileUtil.getFile("component://dazz/models/en-pos-maxent.bin");
        POSModel posModel = new POSModelLoader().load(modelFile);
        return posModel;
    }
    
    public static ChunkerModel trainChunkerModel() throws Exception {
        File file = FileUtil
                .getFile("component://dazz/config/chunker/Chunker.txt");
        InputStream in = new FileInputStream(file);
        String encoding = "UTF-8";

        ObjectStream<ChunkSample> sampleStream = new ChunkSampleStream(
            new PlainTextByLineStream(new InputStreamReader(in, encoding)));
        ChunkerModel chunkerModel = ChunkerME.train("en", sampleStream, 1, 70);
        return chunkerModel;
    }
    
    public static ParserModel trainParserModel() throws Exception {
        File moduleFile = FileUtil.getFile("component://dazz/models/en-parser-chunking.bin");
        InputStream in = new FileInputStream(moduleFile);
        ParserModel model = new ParserModel(in);
        return model;
    }
    
    public static HeadRules createHeadRules() throws IOException {
        File file = FileUtil
                .getFile("component://dazz/config/parser/en_head_rules");
        InputStream in = new FileInputStream(file);
         HeadRules headRules = new HeadRules(new BufferedReader(
                 new InputStreamReader(in, "UTF-8")));
         in.close();
         return headRules;
    }
}
