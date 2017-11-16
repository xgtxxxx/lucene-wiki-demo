package com.b2s.wiki;

import com.b2s.wiki.model.AppConfig;
import com.b2s.wiki.service.IndexWriteService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfiguration {

    @Bean
    public AppConfig appConfig() {
        return AppConfig.newInstance();
    }

    @Bean
    public Directory directory() {
        return new RAMDirectory();
    }

    @Bean
    public Analyzer analyzer() {
        return new StandardAnalyzer();
    }

    @Bean(initMethod = "write")
    public IndexWriteService writeService() {
        return new IndexWriteService();
    }
}
