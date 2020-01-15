package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleListDTO;
import com.hexhoc.springbootblog.category.Category;
import com.hexhoc.springbootblog.category.CategoryRepository;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.tag.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService{

    ArticleRepository articleRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public PageResult getArticlesForIndexPage(int page) {

        int limit = 8;
        Boolean blogStatus = true; //Filter the data in the published state

        List<Article> articlesList = articleRepository.findByStatus(blogStatus,PageRequest.of(page-1, 8, Sort.by(Sort.Direction.ASC, "createTime")));

        List<ArticleListDTO> articlesListDTO = convertToArticleListDTO(articlesList);
        int total = (int) articleRepository.count();

        return new PageResult(articlesListDTO, total, limit, page);
    }

    @Override
    public PageResult getBlogsPageByCategory(String categoryName, Integer page) {

        int limit = 8;
        int total = 0;
        Boolean blogStatus = true; //Filter the data in the published state
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        List<ArticleListDTO> articlesListDTO = new ArrayList<>();

        if(!categoryOptional.isEmpty()) {
            Category category = categoryOptional.get();
            List<Article> articlesList = articleRepository.findByStatusAndCategory(blogStatus, category, PageRequest.of(page - 1, 8, Sort.by(Sort.Direction.ASC, "createTime")));
            articlesListDTO = convertToArticleListDTO(articlesList);
            total = (int) articleRepository.count();
        }
        return new PageResult(articlesListDTO, total, limit, page);
    }

    public ArticleDetailDTO getArticleDetailDTOById(Long id) {

        Optional<Article> articleOptional = articleRepository.findById(id);
        ArticleDetailDTO articleDetailDTO = new ArticleDetailDTO();

        if(articleOptional.isPresent()) {
            articleDetailDTO = convertToArticleDetailDTO(articleOptional.get());
        }

        return articleDetailDTO;
    }



    @Override
    public List<ArticleListDTO> convertToArticleListDTO(List<Article> articlesList) {

        List<ArticleListDTO> articlesListDTO = new ArrayList<>();

        for (Article article: articlesList) {
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article, articleListDTO);

            Category category = article.getCategory();
            articleListDTO.setCategoryId(category.getId());
            articleListDTO.setCategoryName(category.getName());
            articleListDTO.setCategoryIcon(category.getIcon());

            articlesListDTO.add(articleListDTO);
        }

        return articlesListDTO;
    }

    public ArticleDetailDTO convertToArticleDetailDTO(Article article) {

        ArticleDetailDTO articleDetailDTO = new ArticleDetailDTO();
        articleDetailDTO.setId(article.getId());
        articleDetailDTO.setTitle(article.getTitle());
        articleDetailDTO.setCategoryId(article.getCategory().getId());
        articleDetailDTO.setCategoryIcon(article.getCategory().getIcon());
        articleDetailDTO.setCategoryName(article.getCategory().getName());
        //TODO calculate and fill this property
        articleDetailDTO.setCommentCount(0);
        articleDetailDTO.setCoverImage(article.getCoverImage());
        articleDetailDTO.setViews(article.getViews());

        Set<Tag> tags = article.getTags();
        List<String> tagsString = new ArrayList<>();

        for(Tag tag : tags){
            tagsString.add(tag.getName());
        }

        articleDetailDTO.setTags(tagsString);
        articleDetailDTO.setContent(article.getContent());
        articleDetailDTO.setEnableComment(article.getEnableComment());
        articleDetailDTO.setCreateTime(article.getCreateTime());

        return articleDetailDTO;

    }

}
