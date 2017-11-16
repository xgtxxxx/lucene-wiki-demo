package com.b2s.wiki.service;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.model.AppConstants;
import com.b2s.wiki.model.Article;
import com.b2s.wiki.model.Pager;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QueryService {

    @Autowired
    private DataStore dataStore;

    public Pager query(final String keyword, final int lastIndex) {
        try (final DirectoryReader directoryReader = DirectoryReader.open(dataStore.getDirectory())) {
            final IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            final Query query = getQuery(dataStore.getAnalyzer(), keyword);

            final Long totalHits = indexSearcher.search(query, Integer.MAX_VALUE).totalHits;
            final Sort sort = getSort();
            final ScoreDoc lastScoreDoc = getLastScoreDoc(lastIndex, query, indexSearcher, sort);
            final TopDocs topDocs = indexSearcher.searchAfter(lastScoreDoc, query, AppConstants.PAGE_SIZE, sort);
            final Highlighter highlighter = getHighLighter(query);

            final List<Article> documents = new ArrayList<>();
            for (final ScoreDoc scoreDoc : topDocs.scoreDocs) {
                final Document doc = indexSearcher.doc(scoreDoc.doc);
                final String title = doc.get(AppConstants.FIELD.TITLE);
                final String content = doc.get(AppConstants.FIELD.CONTENT);
                final String author = doc.get(AppConstants.FIELD.AUTHOR);
                final String createDate = doc.get(AppConstants.FIELD.CREATE_DATE);
                //Get highlighted text fragments
                final boolean isHighLightNeeded = StringUtils.isNotEmpty(keyword);
                final String highLightTitle = getHighLight(highlighter, dataStore.getAnalyzer(), AppConstants.FIELD.TITLE, title, isHighLightNeeded);
                final String highLightContent =
                    getHighLight(highlighter, dataStore.getAnalyzer(), AppConstants.FIELD.CONTENT, content, isHighLightNeeded);
                final String highLightAuthor =
                    getHighLight(highlighter, dataStore.getAnalyzer(), AppConstants.FIELD.AUTHOR, author, isHighLightNeeded);

                final Article article = new Article();
                article.setId(doc.get(AppConstants.FIELD.ID));
                article.setTitle(Optional.ofNullable(highLightTitle).orElse(title));
                article.setAuthor(Optional.ofNullable(highLightAuthor).orElse(author));
                article.setContent(highLightContent.length()>AppConstants.CONTENT_LENGTH ? highLightContent.substring(0, AppConstants.CONTENT_LENGTH) : highLightContent);
                article.setCreateDate(createDate);

                documents.add(article);
            }

            return new Pager(totalHits, lastIndex + documents.size(), documents);
        } catch (final IOException | ParseException | InvalidTokenOffsetsException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }

    private String getHighLight(
        final Highlighter highlighter,
        final Analyzer analyzer,
        final String field,
        final String text, final boolean isNeeded) throws IOException, InvalidTokenOffsetsException {
        String s = null;
        if(isNeeded) {
            s = highlighter.getBestFragment(analyzer, field, text);
            if(StringUtils.isEmpty(s)){
                s = text;
            }
        } else {
            s = text;
        }

        return s;
    }

    private Query getQuery(final Analyzer analyzer, final String keyword) throws ParseException{
        if(StringUtils.isEmpty(keyword)) {
            return new QueryParser(AppConstants.FIELD.DEFAULT_INDEX, analyzer).parse(AppConstants.FIELD.DEFAULT_INDEX);
        }else{
            final String[] fields = {AppConstants.FIELD.AUTHOR, AppConstants.FIELD.TITLE, AppConstants.FIELD.CONTENT};
            //MUST -> and，MUST_NOT -> not，SHOULD -> or
            final BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
            final Query query = MultiFieldQueryParser.parse(keyword, fields, clauses, analyzer);

            return query;
        }
    }

    private Highlighter getHighLighter(final Query query) {
        /** Highlighter Code Start ****/
        //Uses HTML &lt;B&gt;&lt;/B&gt; tag to highlight the searched terms
        final Formatter formatter = new SimpleHTMLFormatter(AppConstants.PRE_HIGH_TAG, AppConstants.POST_HIGH_TAG);
        //It scores text fragments by the number of unique query terms found
        //Basically the matching score in layman terms
        final QueryScorer scorer = new QueryScorer(query);
        //used to markup highlighted terms found in the best sections of a text
        final Highlighter highlighter = new Highlighter(formatter, scorer);
        //breaks text up into same-size fragments with no concerns over spotting sentence boundaries.
        //The size of the string.
        final Fragmenter fragmenter = new SimpleFragmenter(AppConstants.CONTENT_LENGTH);
        //set fragmenter to highlighter
        highlighter.setTextFragmenter(fragmenter);

        return highlighter;
    }

    private ScoreDoc getLastScoreDoc(
        final int lastIndex,
        final Query query,
        final IndexSearcher indexSearcher,
        final Sort sort) throws IOException {

        if (lastIndex == 0) {
            return null;
        }
        final TopDocs tds = indexSearcher.search(query, lastIndex, sort);

        return tds.scoreDocs[lastIndex - 1];
    }

    private Sort getSort() {
        return new Sort(new SortField(AppConstants.FIELD.CREATE_DATE_TIME, SortField.Type.LONG, true));
    }
}
