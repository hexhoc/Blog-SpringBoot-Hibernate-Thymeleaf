package com.hexhoc.springbootblog.tag;


import com.hexhoc.springbootblog.common.util.PageResult;

import java.util.List;
import java.util.Set;

public interface TagService {

    List<Tag> getTagsWithArticlesCount();

    Long getTotalTags();

    Tag findOrCreateTag(String tagName);

    PageResult getTagsPage(int page, int limit);

}
