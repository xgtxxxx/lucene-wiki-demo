package com.b2s.wiki.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataStore {
    private final Directory directory;
    private final Analyzer analyzer;

    @Autowired
    public DataStore(final Directory directory, final Analyzer analyzer) {
        this.directory = directory;
        this.analyzer = analyzer;
    }

    public Directory getDirectory() {
        return directory;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }
}
