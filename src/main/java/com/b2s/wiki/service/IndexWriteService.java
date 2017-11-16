package com.b2s.wiki.service;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.model.AppConstants;
import com.b2s.wiki.model.Article;
import com.b2s.wiki.transformer.FileToArticleTransformer;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.b2s.wiki.model.AppConstants.FIELD.*;

public class IndexWriteService {

    @Value("${wiki.config.path.articles}")
    private String articleSavedPath;

    @Autowired
    private DataStore dataStore;

    @Autowired
    private FileToArticleTransformer transformer;

    public void write() {
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(dataStore.getAnalyzer());
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        final List<File> files = Arrays.asList(new File(articleSavedPath).listFiles());
        final List<Document> documents = Optional
            .ofNullable(files)
            .orElse(Collections.emptyList())
            .stream()
            .map(file -> transformToDocument(transformer.apply(file)))
            .collect(Collectors.toList());
        try (final IndexWriter indexWriter = new IndexWriter(dataStore.getDirectory(), indexWriterConfig)) {
            indexWriter.addDocuments(documents);
            indexWriter.commit();
        } catch (final IOException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }

    public void add(final Article article) {
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(dataStore.getAnalyzer());
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
        try (final IndexWriter indexWriter = new IndexWriter(dataStore.getDirectory(), indexWriterConfig)) {
            indexWriter.addDocument(transformToDocument(article));
            indexWriter.commit();
        } catch (final IOException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }

    public void delete(final String id) {
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(dataStore.getAnalyzer());
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.APPEND);

        try (final IndexWriter indexWriter = new IndexWriter(dataStore.getDirectory(), indexWriterConfig)) {
            final Query query = new TermQuery(new Term(ID_USED_TO_DELETE, String.valueOf(getCreateTime(id))));
            indexWriter.deleteDocuments(query);
            indexWriter.commit();
            indexWriter.flush();
            indexWriter.close();
        } catch (final IOException|ParseException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }

    private Document transformToDocument(final Article article) {
        final Document document = new Document();
        try{
            document.add(new Field(ID, article.getId(), TextField.TYPE_STORED));
            document.add(new Field(ID_USED_TO_DELETE, String.valueOf(getCreateTime(article.getId())), TextField.TYPE_STORED));
            document.add(new Field(CONTENT, Jsoup.parse(article.getContent()).text(), TextField.TYPE_STORED));
            document.add(new Field(TITLE, article.getTitle(), TextField.TYPE_STORED));
            document.add(new Field(AUTHOR, article.getAuthor(), TextField.TYPE_STORED));
            document.add(new Field(CREATE_DATE, article.getCreateDate(), TextField.TYPE_STORED));
            document.add(new Field(DEFAULT_INDEX, DEFAULT_INDEX, TextField.TYPE_STORED));
            //Must to be NumericDocValuesField or SortedNumericDocValuesField, then you can sort by this field.
            document.add(new NumericDocValuesField(CREATE_DATE_TIME, getCreateTime(article.getId())));
        }catch (final ParseException|IOException e) {
            throw new WikiException(e.getMessage(), e);
        }

        return document;
    }

    private long getCreateTime(final String id) throws IOException, ParseException {
        final String dateTime = new String(Base64.getDecoder().decode(id), "UTF-8").split("_")[1];

        return DateUtils.parseDate(dateTime, AppConstants.DATE_TIME_FORMAT).getTime();
    }
}
