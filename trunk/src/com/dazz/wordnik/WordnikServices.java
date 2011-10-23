/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik;

import java.util.Map;

import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;

public class WordnikServices {

    public final static String module = WordnikServices.class.getName();
    
    /**
     * get word
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWord(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        boolean includeSuggestions = (Boolean) context.get("includeSuggestions");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word audio
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordAudio(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word definitions
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordDefinitions(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        int limit = (Integer) context.get("limit");
        String partOfSpeech = (String) context.get("partOfSpeech");
        boolean includeRelated = (Boolean) context.get("includeRelated");
        String sourceDictionary = (String) context.get("sourceDictionary");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        boolean includeTags = (Boolean) context.get("includeTags");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word examples
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordExamples(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean includeDuplicates = (Boolean) context.get("includeDuplicates");
        String contentProvider = (String) context.get("contentProvider");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        String skip = (String) context.get("skip");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word frequency
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordFrequency(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        String startYear = (String) context.get("startYear");
        String endYear = (String) context.get("endYear");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word hyphenation
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordHyphenation(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        String sourceDictionary = (String) context.get("sourceDictionary");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word of the day
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordOfTheDay(DispatchContext ctx, Map<String, Object> context) {
        String date = (String) context.get("date");
        String category = (String) context.get("category");
        String creator = (String) context.get("creator");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word phrases
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordPhrases(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        int limit = (Integer) context.get("limit");
        int wlmi = (Integer) context.get("wlmi");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word pronunciations
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordPronunciations(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        String sourceDictionary = (String) context.get("sourceDictionary");
        String typeFormat = (String) context.get("typeFormat");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word related
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordRelated(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        String partOfSpeech = (String) context.get("partOfSpeech");
        String sourceDictionary = (String) context.get("sourceDictionary");
        int limit = (Integer) context.get("limit");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        String type = (String) context.get("type");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * get word top example
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> getWordTopExample(DispatchContext ctx, Map<String, Object> context) {
        String word = (String) context.get("word");
        String contentProvider = (String) context.get("contentProvider");
        boolean useCanonical = (Boolean) context.get("useCanonical");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * random word
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> randomWord(DispatchContext ctx, Map<String, Object> context) {
        boolean hasDictionaryDef = (Boolean) context.get("hasDictionaryDef");
        String includePartOfSpeech = (String) context.get("includePartOfSpeech");
        String excludePartOfSpeech = (String) context.get("excludePartOfSpeech");
        int minCorpusCount = (Integer) context.get("minCorpusCount");
        int maxCorpusCount = (Integer) context.get("maxCorpusCount");
        int minDictionaryCount = (Integer) context.get("minDictionaryCount");
        int maxDictionaryCount = (Integer) context.get("maxDictionaryCount");
        int minLength = (Integer) context.get("minLength");
        int maxLength = (Integer) context.get("maxLength");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * random words
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> randomWords(DispatchContext ctx, Map<String, Object> context) {
        boolean hasDictionaryDef = (Boolean) context.get("hasDictionaryDef");
        String includePartOfSpeech = (String) context.get("includePartOfSpeech");
        String excludePartOfSpeech = (String) context.get("excludePartOfSpeech");
        int minCorpusCount = (Integer) context.get("minCorpusCount");
        int maxCorpusCount = (Integer) context.get("maxCorpusCount");
        int minDictionaryCount = (Integer) context.get("minDictionaryCount");
        int maxDictionaryCount = (Integer) context.get("maxDictionaryCount");
        int minLength = (Integer) context.get("minLength");
        int maxLength = (Integer) context.get("maxLength");
        String sortBy = (String) context.get("sortBy");
        String sortOrder = (String) context.get("sortOrder");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * search query word
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> searchQueryWord(DispatchContext ctx, Map<String, Object> context) {
        String query = (String) context.get("query");
        boolean caseSensitive = (Boolean) context.get("caseSensitive");
        String includePartOfSpeech = (String) context.get("includePartOfSpeech");
        String excludePartOfSpeech = (String) context.get("excludePartOfSpeech");
        int minCorpusCount = (Integer) context.get("minCorpusCount");
        int maxCorpusCount = (Integer) context.get("maxCorpusCount");
        int minDictionaryCount = (Integer) context.get("minDictionaryCount");
        int maxDictionaryCount = (Integer) context.get("maxDictionaryCount");
        int minLength = (Integer) context.get("minLength");
        int maxLength = (Integer) context.get("maxLength");
        int skip = (Integer) context.get("skip");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
    
    /**
     * search word
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> searchWord(DispatchContext ctx, Map<String, Object> context) {
        String query = (String) context.get("query");
        boolean caseSensitive = (Boolean) context.get("caseSensitive");
        String includePartOfSpeech = (String) context.get("includePartOfSpeech");
        String excludePartOfSpeech = (String) context.get("excludePartOfSpeech");
        int minCorpusCount = (Integer) context.get("minCorpusCount");
        int maxCorpusCount = (Integer) context.get("maxCorpusCount");
        int minDictionaryCount = (Integer) context.get("minDictionaryCount");
        int maxDictionaryCount = (Integer) context.get("maxDictionaryCount");
        int minLength = (Integer) context.get("minLength");
        int maxLength = (Integer) context.get("maxLength");
        int skip = (Integer) context.get("skip");
        int limit = (Integer) context.get("limit");
        
        return ServiceUtil.returnSuccess();
    }
}
