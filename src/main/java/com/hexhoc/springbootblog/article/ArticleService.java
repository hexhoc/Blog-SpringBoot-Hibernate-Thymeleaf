package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleListDTO;
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

    PageResult getBlogsPageByCategory(String categoryName, Integer page);

    List<ArticleListDTO> convertToArticleListDTO(List<Article> articlesList);

    ArticleDetailDTO getArticleDetailDTOById(Long id);

    ArticleDetailDTO convertToArticleDetailDTO(Article article);

    Long getTotalArticle();

}
