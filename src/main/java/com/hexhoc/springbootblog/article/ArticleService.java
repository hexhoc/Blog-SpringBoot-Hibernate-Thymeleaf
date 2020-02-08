package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleEditDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleListDTO;
import com.hexhoc.springbootblog.common.util.PageResult;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> getArticleById(Long articleId);

    /**
     * Get a list of homepage articles
     *
     * @param page
     * @return
     */
    PageResult getArticlesForIndexPage(int page);

    PageResult getArticlesPage(int page, int limit);

    PageResult getBlogsPageByCategory(String categoryName, Integer page);

    List<ArticleListDTO> convertToArticleListDTO(List<Article> articlesList);

    ArticleDetailDTO getArticleDetailDTOById(Long id);

    ArticleDetailDTO convertToArticleDetailDTO(Article article);

    Article convertArticleEditDTOToArticle(ArticleEditDTO articleEditDTO);

    Long getTotalArticle();

    void saveArticle(Article article);

    String saveArticle(ArticleEditDTO articleEditDTO);

    String updateArticle(Article article);

    String updateArticle(ArticleEditDTO articleEditDTO);

}
