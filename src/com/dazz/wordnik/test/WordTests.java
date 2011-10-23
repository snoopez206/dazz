/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik.test;

import java.util.List;
import java.util.Map;

import javolution.util.FastMap;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.ModelService;
import org.ofbiz.service.testtools.OFBizTestCase;

import com.dazz.wordnik.dao.Word;

public class WordTests extends OFBizTestCase {
    
    public final static String module = WordTests.class.getName();

    protected GenericValue userLogin = null;

    public WordTests(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        userLogin = delegator.findByPrimaryKey("UserLogin", UtilMisc.toMap("userLoginId", "system"));
    }

    public void testRandomWords() throws Exception {
        Map<String, Object> ctx = FastMap.newInstance();
        ctx.put("userLogin", userLogin);
        Map<String, Object> results = dispatcher.runSync("randomWords", ctx);
        assertEquals("Service result success", ModelService.RESPOND_SUCCESS, results.get(ModelService.RESPONSE_MESSAGE));
        List<Word> words = (List<Word>) results.get("words");
        assertNotSame(0, words.size());
        for (Word word : words) {
            Debug.logInfo("word: " + word, module);
        }
    }
}
