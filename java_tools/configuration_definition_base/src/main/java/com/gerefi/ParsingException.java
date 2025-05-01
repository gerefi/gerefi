package com.gerefi;

public class ParsingException extends RuntimeException {
    public ParsingException(String s, Throwable e) {
        super(s, e);
    }
}
