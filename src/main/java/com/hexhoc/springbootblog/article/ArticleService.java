package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.common.util.PageResult;

import java.util.List;

public interface ArticleService {

    /**
     * Get a list of homepage articles
     *
     * @param page
     * @return
     */
    PageResult getArticlesForIndexPage(int page);

    public List<ArticleListDTO> convertToArticleListDTO(List<Article> articlesList);
}
