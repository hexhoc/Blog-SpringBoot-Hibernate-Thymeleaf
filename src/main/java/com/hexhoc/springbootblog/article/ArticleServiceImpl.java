package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleEditDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleListDTO;
import com.hexhoc.springbootblog.category.Category;
import com.hexhoc.springbootblog.category.CategoryRepository;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.tag.Tag;
import com.hexhoc.springbootblog.tag.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService{

    ArticleRepository articleRepository;
    CategoryRepository categoryRepository;
    TagService tagService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              CategoryRepository categoryRepository,
                              TagService tagService){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.tagService = tagService;
    }


    public Optional<Article> getArticleById(Long articleId){
        return articleRepository.findById(articleId);
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
    public PageResult getArticlesPage(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        List<Article> articlesPage = articleRepository.findAll(pageRequest).getContent();
        int total = (int)articleRepository.count();
        PageResult pageResult = new PageResult(articlesPage, total, limit, page);

        return pageResult;
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
    public ArticleDetailDTO getArticleDetailDTOById(Long id) {

        Optional<Article> articleOptional = articleRepository.findById(id);
        ArticleDetailDTO articleDetailDTO = new ArticleDetailDTO();

        if(articleOptional.isPresent()) {
            articleDetailDTO = convertToArticleDetailDTO(articleOptional.get());
        }

        return articleDetailDTO;
    }

    @Override
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

    @Override
    public Article convertArticleEditDTOToArticle(ArticleEditDTO articleEditDTO) {

        Optional<Category> categoryOptional = categoryRepository.findById(articleEditDTO.getBlogCategoryId());
        Set<Tag> TagsList = Arrays.stream(articleEditDTO.getBlogTags().split(",|\\.| "))
                .map(tagService::findOrCreateTag)
                .collect(Collectors.toCollection(HashSet::new));

        Article article = new Article();
        article.setTitle(articleEditDTO.getBlogTitle());
        article.setSubUrl(articleEditDTO.getBlogSubUrl());
        article.setCategory(categoryOptional.get());
        article.setTags(TagsList);
        article.setContent(articleEditDTO.getBlogContent());
        article.setCoverImage(articleEditDTO.getBlogCoverImage());
        article.setStatus(articleEditDTO.getBlogStatus());
        article.setEnableComment(articleEditDTO.getEnableComment());

        return article;
    }

    @Override
    public Long getTotalArticle() {
        return articleRepository.count();
    }

    @Override
    public void saveArticle(Article article) {
       articleRepository.save(article);
    }

    @Override
    public String saveArticle(ArticleEditDTO articleEditDTO) {

        String validateResult = articleEditDTO.validateArticle();
        if (validateResult.length() > 0) {
            return validateResult;
        }

        Article article = convertArticleEditDTOToArticle(articleEditDTO);
        article.setCreateTime(LocalDateTime.now());
        article.setIsDeleted(false);
        article.setViews(0L);
        articleRepository.save(article);

        return "success";
    }

    @Override
    public String updateArticle(Article article) {

        String result = "";

        Optional<Article> articleOptional = articleRepository.findById(article.getId());

        if (articleOptional.isEmpty()) {
            result = "article is not found";
        } else {
            article.setId(articleOptional.get().getId());
            articleRepository.save(article);
            result = "success";
        }

        return result;
    }

    @Override
    public String updateArticle(ArticleEditDTO articleEditDTO) {
        String result = "";

        Optional<Article> articleOptional = articleRepository.findById(articleEditDTO.getBlogId());

        if (articleOptional.isEmpty()) {
            result = "article is not found";
        } else {
            Article articleEdited = convertArticleEditDTOToArticle(articleEditDTO);
            Article articleCurrent = articleOptional.get();

            //TODO normal convert DTO to Entity
            articleCurrent.setUpdateTime(LocalDateTime.now());
            articleCurrent.setTitle(articleEdited.getTitle());
            articleCurrent.setSubUrl(articleEdited.getSubUrl());
            articleCurrent.setCategory(articleEdited.getCategory());
            articleCurrent.setTags(articleEdited.getTags());
            articleCurrent.setContent(articleEdited.getContent());
            articleCurrent.setCoverImage(articleEdited.getCoverImage());
            articleCurrent.setStatus(articleEdited.getStatus());
            articleCurrent.setEnableComment(articleEdited.getEnableComment());

            articleRepository.save(articleCurrent);
            result = "success";
        }

        return result;
    }

    @Override
    public String getTagsListAsString(Set<Tag> tagsSet) {

        return tagsSet.stream()
                .map(Tag::getName)
                .collect(Collectors.joining(", "));
//        Set<String> simpleTagsList = new HashSet<>();
//
//        for(Tag tag : tagsSet) {
//            simpleTagsList.add(tag.getName());
//        }
//
//        return simpleTagsList;
    }

    @Override
    public Boolean deleteBatch(ArrayList<Long> ids) {

        //// TODO: 21.07.2021 Need try catch
        List<Article> articlesList = articleRepository.findAllById(ids);
        articleRepository.saveAll(articlesList);

        return true;
    }


}
