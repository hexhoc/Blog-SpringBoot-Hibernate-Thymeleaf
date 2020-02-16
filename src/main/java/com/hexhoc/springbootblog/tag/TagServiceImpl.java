package com.hexhoc.springbootblog.tag;

import com.hexhoc.springbootblog.common.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            tag.setCreateTime(LocalDateTime.now());
            tag.setIsDeleted(false);
            tagRepository.save(tag);
        } else {
            tag = tagOptional.get();
        }

        return tag;
    }

    @Override
    public PageResult getTagsPage(int page, int limit) {
        List<Tag> tagsPage = tagRepository.findAll(PageRequest.of(page-1, limit)).getContent();
        int total = (int) tagRepository.count();
        PageResult pageResult = new PageResult(tagsPage, total, limit, page);

        return pageResult;
    }

    @Override
    public Boolean saveTag(String tagName) {
        // TODO: 23.07.2021 add try catch and transaction

        Optional<Tag> existTag = tagRepository.findByName(tagName);
        if (existTag.isPresent()) {
            return false;
        } else {
            Tag tag = new Tag();
            tag.setName(tagName);
            tag.setCreateTime(LocalDateTime.now());
            tag.setIsDeleted(false);
            tagRepository.save(tag);
            return true;
        }
    }

    @Override
    public Boolean deleteBatch(ArrayList<Integer> ids) {
        // TODO: 23.07.2021 add try catch and transaction
        List<Tag> tagsList = tagRepository.findAllById(ids);
        tagRepository.deleteAll(tagsList);
        return true;
    }


}
