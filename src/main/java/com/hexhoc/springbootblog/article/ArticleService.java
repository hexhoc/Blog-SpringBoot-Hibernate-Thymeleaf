package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.common.util.PageResult;

public interface ArticleService {

    /**
     * Get a list of homepage articles
     *
     * @param page
     * @return
     */
    PageResult getArticlesForIndexPage(int page);

}
