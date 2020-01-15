package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.category.Category;
import com.hexhoc.springbootblog.category.CategoryRepository;
import com.hexhoc.springbootblog.common.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
