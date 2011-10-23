/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.wordnik.dao;

public interface Word {

    public final static String module = Word.class.getName();
    
    public String getText();
    public void setText(String text);
}
