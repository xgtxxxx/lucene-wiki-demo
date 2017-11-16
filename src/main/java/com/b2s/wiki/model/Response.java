package com.b2s.wiki.model;

public class Response {
    private final boolean success;
    private final String message;

    public Response(final boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
