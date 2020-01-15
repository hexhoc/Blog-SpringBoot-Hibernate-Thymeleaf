package com.hexhoc.springbootblog.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTagsWithArticlesCount() {
        return tagRepository.getTagsWithArticlesCount();
    }

}
