package com.b2s.wiki.transformer;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.model.Article;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.function.Function;

@Component
public class FileToArticleTransformer implements Function<File, Article> {

    @Override
    public Article apply(final File file) {
        try {
            final String id = file.getName();
            final String name = new String(Base64.getDecoder().decode(id.replaceAll("_", "/")));
            final String[] attrs = name.split("_");
            final Article article = new Article();
            article.setId(id);
            article.setAuthor(attrs[0]);
            article.setCreateDate(attrs[1]);
            article.setTitle(attrs[2]);
            article.setContent(Jsoup.parse(file, "UTF-8").html());

            return article;
        } catch (final IOException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }
}
