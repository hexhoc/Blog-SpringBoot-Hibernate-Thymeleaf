package com.hexhoc.springbootblog.article.DTO;

import com.hexhoc.springbootblog.common.util.PostResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

public class ArticleEditDTO {

    private Long blogId;
    private String blogTitle;
    private String blogSubUrl;
    private Integer blogCategoryId;
    private String blogTags;
    private String blogContent;
    private String blogCoverImage;
    private Boolean blogStatus;
    private Boolean enableComment;

    public ArticleEditDTO(Long blogId,
                          String blogTitle,
                          String blogSubUrl,
                          Integer blogCategoryId,
                          String blogTags,
                          String blogContent,
                          String blogCoverImage,
                          Boolean blogStatus,
                          Boolean enableComment) {

        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogSubUrl = blogSubUrl;
        this.blogCategoryId = blogCategoryId;
        this.blogTags = blogTags;
        this.blogContent = blogContent;
        this.blogCoverImage = blogCoverImage;
        this.blogStatus = blogStatus;
        this.enableComment = enableComment;
        
    }
    
    public String validateArticle(){
        
        if (StringUtils.isEmpty(this.blogTitle)) {
            return "Please enter the title of the article";
        }
        if (this.blogTitle.trim().length()> 150) {
            return "Title is too long";
        }
        if (StringUtils.isEmpty(this.blogTags)) {
            return "Please enter the article tag";
        }
        if (this.blogTags.trim().length()> 150) {
            return "Label is too long";
        }
        if (this.blogSubUrl.trim().length()> 150) {
            return "The path is too long";
        }
        if (StringUtils.isEmpty(this.blogContent)) {
            return "Please enter the content of the article";
        }
        if (this.blogContent.trim().length()> 100000) {
            return "The article content is too long";
        }
        if (StringUtils.isEmpty(this.blogCoverImage)) {
            return "The cover image cannot be empty";
        }

        return "";

    }


    /**
     * GETTER AND SETTER
     *
     */

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSubUrl() {
        return blogSubUrl;
    }

    public void setBlogSubUrl(String blogSubUrl) {
        this.blogSubUrl = blogSubUrl;
    }

    public Integer getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogCoverImage() {
        return blogCoverImage;
    }

    public void setBlogCoverImage(String blogCoverImage) {
        this.blogCoverImage = blogCoverImage;
    }

    public Boolean getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Boolean blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Boolean getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Boolean enableComment) {
        this.enableComment = enableComment;
    }
}
