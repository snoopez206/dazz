/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javolution.util.FastList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.HttpClient;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;

import com.dazz.wordnik.dao.Word;
import com.dazz.wordnik.dao.WordImpl;
import com.dazz.wordnik.util.WordnikUtil;

public class WordServices {

    public final static String module = WordServices.class.getName();
    
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
        boolean hasDictionaryDef = false;
        String includePartOfSpeech = (String) context.get("includePartOfSpeech");
        String excludePartOfSpeech = (String) context.get("excludePartOfSpeech");
        int minCorpusCount = 0;
        int maxCorpusCount = 20;
        int minDictionaryCount = 0;
        int maxDictionaryCount = 20;
        int minLength = 0;
        int maxLength = 20;
        String sortBy = (String) context.get("sortBy");
        String sortOrder = (String) context.get("sortOrder");
        int limit = 20;

        if (UtilValidate.isNotEmpty(context.get("hasDictionaryDef"))) {
            hasDictionaryDef = (Boolean) context.get("hasDictionaryDef");
        }
        if (UtilValidate.isNotEmpty(context.get("minCorpusCount"))) {
            minCorpusCount = (Integer) context.get("minCorpusCount");
        }
        if (UtilValidate.isNotEmpty(context.get("maxCorpusCount"))) {
            maxCorpusCount = (Integer) context.get("maxCorpusCount");
        }
        if (UtilValidate.isNotEmpty(context.get("minDictionaryCount"))) {
            minDictionaryCount = (Integer) context.get("minDictionaryCount");
        }
        if (UtilValidate.isNotEmpty(context.get("maxDictionaryCount"))) {
            maxDictionaryCount = (Integer) context.get("maxDictionaryCount");
        }
        if (UtilValidate.isNotEmpty(context.get("minLength"))) {
            minLength = (Integer) context.get("minLength");
        }
        if (UtilValidate.isNotEmpty(context.get("maxLength"))) {
            maxLength = (Integer) context.get("maxLength");
        }
        if (UtilValidate.isNotEmpty(context.get("limit"))) {
            limit = (Integer) context.get("limit");
        }
        
        try {
            String apiUrl = WordnikUtil.getApiUrl();
            String apiKey = WordnikUtil.getApiKey();
            HttpClient httpClient = new HttpClient(new URL(apiUrl + "/randomWords"));
            httpClient.setHeader("api_key", apiKey);
            httpClient.setParameter("includePartOfSpeech", includePartOfSpeech);
            httpClient.setParameter("excludePartOfSpeech", excludePartOfSpeech);
            httpClient.setParameter("hasDictionaryDef", String.valueOf(hasDictionaryDef));
            httpClient.setParameter("minCorpusCount", String.valueOf(minCorpusCount));
            httpClient.setParameter("maxCorpusCount", String.valueOf(maxCorpusCount));
            httpClient.setParameter("minDictionaryCount", String.valueOf(minDictionaryCount));
            httpClient.setParameter("maxDictionaryCount", String.valueOf(maxDictionaryCount));
            httpClient.setParameter("minLength", String.valueOf(minLength));
            httpClient.setParameter("maxLength", String.valueOf(maxLength));
            httpClient.setParameter("sortBy", sortBy);
            httpClient.setParameter("sortOrder", sortOrder);
            httpClient.setParameter("limit", String.valueOf(limit));
            String result = httpClient.get();

            List<Word> words = FastList.newInstance();
            
            JSONArray jsonArray = JSONArray.fromObject(result);
            ListIterator<JSONObject> listIter = jsonArray.listIterator();
            while (listIter.hasNext()) {
                JSONObject jsonObject = listIter.next();
                String text = (String) jsonObject.get("word");
                Word word = new WordImpl();
                word.setText(text);
                words.add(word);
            }
            
            Map<String, Object> results = ServiceUtil.returnSuccess();
            results.put("words", words);
            return results;
        } catch (Exception e) {
            Debug.logError(e, module);
            return ServiceUtil.returnError(e.getMessage());
        }
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
