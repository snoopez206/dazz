/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik.util;

import org.ofbiz.base.util.UtilProperties;

public class WordnikUtil {

    public final static String module = WordnikUtil.class.getName();
    
    /**
     * get API URL
     * @return
     */
    public static String getApiUrl() {
        String apiUrl = UtilProperties.getPropertyValue("wordnik", "apiUrl");
        String apiVersion = UtilProperties.getPropertyValue("wordnik", "apiVersion");
        String requestUrl = apiUrl + "/v" + apiVersion + "/words.json";
        return requestUrl;
    }
    
    /**
     * get API key
     * @return
     */
    public static String getApiKey() {
        String apiKey = UtilProperties.getPropertyValue("wordnik", "apiKey");
        return apiKey;
    }
}
