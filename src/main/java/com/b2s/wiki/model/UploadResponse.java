package com.b2s.wiki.model;

public class UploadResponse {
    private final String state;
    private final long size;
    private final String title;
    private final String url;
    private final String type;
    private final String original;

    public UploadResponse(
        final String state,
        final long size,
        final String title,
        final String url,
        final String type,
        final String original) {
        this.state = state;
        this.size = size;
        this.title = title;
        this.url = url;
        this.type = type;
        this.original = original;
    }

    public String getState() {
        return state;
    }

    public long getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getOriginal() {
        return original;
    }
}
