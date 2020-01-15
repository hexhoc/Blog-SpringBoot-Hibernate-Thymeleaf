package com.hexhoc.springbootblog.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByStatus(Boolean status, Pageable pageable);
    List<Article> findAllByViews(Long views);
    List<Article> findByViews(Long views);
    List<Article> findByViewsGreaterThan(Long views);

}
