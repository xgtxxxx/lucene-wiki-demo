package com.b2s.wiki.model;

import java.util.List;

public class Pager {
    private final long total;
    private final int currentIndex;
    private final List<Article> articles;

    public Pager(final long total, final int currentIndex, final List<Article> articles) {
        this.total = total;
        this.currentIndex = currentIndex;
        this.articles = articles;
    }

    public long getTotal() {
        return total;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
