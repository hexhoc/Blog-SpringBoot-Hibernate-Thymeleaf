package com.hexhoc.springbootblog.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    long countAll();

    List<Article> findAllByStatus(Pageable pageable, int status);
}
