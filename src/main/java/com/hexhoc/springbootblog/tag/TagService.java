package com.hexhoc.springbootblog.tag;


import com.hexhoc.springbootblog.common.util.PageResult;

import java.util.ArrayList;
import java.util.List;

public interface TagService {

    List<Tag> getTagsWithArticlesCount();

    Long getTotalTags();

    Tag findOrCreateTag(String tagName);

    PageResult getTagsPage(int page, int limit);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(ArrayList<Integer> ids);
}
