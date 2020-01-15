package com.hexhoc.springbootblog.tag;


import java.util.List;

public interface TagService {

    List<Tag> getTagsWithArticlesCount();
}
