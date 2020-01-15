package com.hexhoc.springbootblog.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query(value = "SELECT\n" +
            "    t.tag_id Id,\n" +
            "    tags.name,\n" +
            "    tags.is_deleted,\n" +
            "    tags.create_time,\n" +
            "    10 tagСount\n" +
            "FROM articles_tags_relation as t\n" +
            "         LEFT JOIN articles\n" +
            "                   ON t.article_id = articles.id\n" +
            "         LEFT JOIN tags ON t.tag_id = tags.id\n" +
            "WHERE articles.is_deleted = 0\n" +
            "GROUP BY t.tag_id,\n" +
            "         tags.name\n", nativeQuery = true)
    List<Tag> getTagsWithArticlesCount();
}
