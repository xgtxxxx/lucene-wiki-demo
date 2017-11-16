package com.b2s.wiki.exception;

public class WikiException extends RuntimeException {
    public WikiException(final String s) {
        super(s);
    }
    public WikiException(final String s, final Exception e) {
        super(s, e);
    }
}
