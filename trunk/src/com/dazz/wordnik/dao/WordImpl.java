/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik.dao;

public class WordImpl implements Word {
    
    public final static String module = WordImpl.class.getName();
    
    protected String text = null;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
