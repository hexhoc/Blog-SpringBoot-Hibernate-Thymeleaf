package com.hexhoc.springbootblog.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hexhoc.springbootblog.category.Category;
import com.hexhoc.springbootblog.tag.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20) COMMENT 'blog table primary key id'")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) COMMENT 'Article title'")
    private String title;

    @Column(name = "content", columnDefinition = "MEDIUMTEXT NOT NULL COMMENT 'blog content'")
    private String content;

    @Column(name = "sub_url", columnDefinition = "VARCHAR(255) COMMENT 'Article custom path url'")
    private String subUrl;

    @Column(name = "cover_image", columnDefinition = "VARCHAR(200) NOT NULL COMMENT 'blog cover image'")
    private String coverImage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id",  columnDefinition = "INT(11) NOT NULL COMMENT 'blog category id'")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="articles_tags_relation",
            joinColumns         = @JoinColumn(name="article_id", referencedColumnName="id"),
            inverseJoinColumns  = @JoinColumn(name="tag_id", referencedColumnName="id")
    )
    private Set<Tag> tags;

    @Column(name = "status", columnDefinition = "BOOLEAN NOT NULL DEFAULT '0' COMMENT '0-draft 1-post'")
    private Boolean status;

    @Column(name = "views", columnDefinition = "BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'How many times the article looked'")
    private Long views;

    @Column(name = "enable_comment", columnDefinition = "BOOLEAN NOT NULL DEFAULT '0' COMMENT '0-allow comment 1-not allow comment'")
    private Boolean enableComment;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Add time'")
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'modification time'")
    private LocalDateTime updateTime;


    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Boolean getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Boolean enableComment) {
        this.enableComment = enableComment;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    ////////////////////////////
    //OVERRIDE METHODS
    ////////////////////////////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", title=").append(this.title);
        sb.append(", subUrl=").append(this.subUrl);
        sb.append(", coverImage=").append(this.coverImage);
        sb.append(", categoryId=").append(this.category.getId());
        sb.append(", categoryName=").append(this.category.getName());
        sb.append(", tags=").append(this.tags);
        sb.append(", status=").append(status);
        sb.append(", views=").append(views);
        sb.append(", comment=").append(enableComment);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}