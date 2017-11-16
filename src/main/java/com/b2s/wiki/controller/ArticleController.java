package com.b2s.wiki.controller;

import com.b2s.wiki.model.AppConstants;
import com.b2s.wiki.model.Article;
import com.b2s.wiki.model.Pager;
import com.b2s.wiki.model.Response;
import com.b2s.wiki.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    @ResponseBody
    public Response save(@RequestBody final Article article) {
        article.setAuthor("Gavin");
        article.setCreateDate(DateFormatUtils.format(new Date(), AppConstants.DATE_TIME_FORMAT));
        if(StringUtils.isNotEmpty(article.getId())) {
            articleService.delete(article.getId());
        }
        articleService.save(article);

        return new Response(true, "success");
    }

    @GetMapping("/article")
    @ResponseBody
    public Pager query(@RequestParam(required = false) final String keyword, @RequestParam final int index) {
        return articleService.query(keyword, index);
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public Article get(@PathVariable final String id) {
        return articleService.get(id);
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public Response delete(@PathVariable final String id) {
        articleService.delete(id);

        return new Response(true, "success");
    }
}
