package com.b2s.wiki.service;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.model.AppConstants;
import com.b2s.wiki.model.Article;
import com.b2s.wiki.model.Pager;
import com.b2s.wiki.transformer.FileToArticleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Service
public class ArticleService {

    @Value("${wiki.config.path.articles}")
    private String articleSavedPath;

    @Autowired
    private IndexWriteService indexWriteService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private FileToArticleTransformer transformer;

    public void save(final Article article) {
        final String filePath = populateFilePath(article);
        try(final FileOutputStream fileOutputStream = new FileOutputStream(articleSavedPath + filePath)) {
            article.setId(filePath);
            fileOutputStream.write(article.getContent().getBytes());
            indexWriteService.add(article);
        } catch (final IOException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }

    public void delete(final String id) {
        final File file = new File(articleSavedPath + id);
        file.delete();
        indexWriteService.delete(id);
    }

    public Pager query(final String keyword, final int index) {
        return queryService.query(keyword, index);
    }

    public Article get(final String id) {
        final File html = new File(articleSavedPath + id);
        return transformer.apply(html);
    }

    private String populateFilePath(final Article article) {
        final StringBuffer path = new StringBuffer()
            .append(article.getAuthor())
            .append('_')
            .append(article.getCreateDate())
            .append('_')
            .append(article.getTitle())
            .append('_')
            .append(Math.abs(new Random().nextInt()));

        return Base64.getEncoder().encodeToString(path.toString().getBytes()).replaceAll("/", "_");
    }
}
