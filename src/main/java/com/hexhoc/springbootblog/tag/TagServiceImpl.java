package com.hexhoc.springbootblog.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTagsWithArticlesCount() {
        return tagRepository.getTagsWithArticlesCount();
    }

    @Override
    public Long getTotalTags() {
        return tagRepository.count();
    }

    @Override
    public Tag findOrCreateTag(String tagName) {

        Tag tag = new Tag(tagName);

        Optional<Tag> tagOptional = tagRepository.findByName(tagName);
        if (tagOptional.isEmpty()) {
            tagRepository.save(tag);
        } else {
            tag = tagOptional.get();
        }

        return tag;
    }

}
