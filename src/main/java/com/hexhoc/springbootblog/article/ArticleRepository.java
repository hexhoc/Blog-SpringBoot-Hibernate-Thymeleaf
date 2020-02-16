package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.category.Category;
import com.hexhoc.springbootblog.tag.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByStatus(Boolean status, Pageable pageable);

    List<Article> findByStatusAndCategory(Boolean status, Category category, Pageable pageable);

    List<Article> findByStatusAndViews(Boolean status, Long views, Pageable pageable);

    List<Article> findByStatusAndTags(Boolean status, Tag tag, Pageable pageable);
}
