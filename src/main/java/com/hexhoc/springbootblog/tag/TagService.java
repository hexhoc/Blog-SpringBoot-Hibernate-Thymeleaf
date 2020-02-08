package com.hexhoc.springbootblog.tag;


import java.util.List;
import java.util.Set;

public interface TagService {

    List<Tag> getTagsWithArticlesCount();

    Long getTotalTags();

    Tag findOrCreateTag(String tagName);

}
