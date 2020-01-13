package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.common.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }


    @Override
    public PageResult getArticlesForIndexPage(int page) {

        int limit = 8;
        int blogStatus = 1; //Filter the data in the published state

        List<Article> articlesPage = articleRepository.findAllByStatus(PageRequest.of(page, 8, Sort.by(Sort.Direction.ASC, "createTime")), blogStatus);
        int total = (int) articleRepository.countAll();

        return new PageResult(articlesPage, total, limit, page);
    }


}
