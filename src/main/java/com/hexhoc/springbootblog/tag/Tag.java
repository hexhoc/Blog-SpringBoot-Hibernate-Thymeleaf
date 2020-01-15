package com.hexhoc.springbootblog.tag;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hexhoc.springbootblog.article.Article;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) NOT NULL COMMENT 'tag table primary key id'")
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(100) NOT NULL COMMENT 'tag name'")
    private String name;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time'")
    private LocalDateTime createTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="articles_tags_relation",
            joinColumns         = @JoinColumn(name="tag_id", referencedColumnName="id"),
            inverseJoinColumns  = @JoinColumn(name="article_id", referencedColumnName="id")
    )
    Set<Article> articles;

    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    ////////////////////////////
    //OVERRIDE METHODS
    ////////////////////////////

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(this.id);
        sb.append(", tagName=").append(this.name);
        sb.append(", isDeleted=").append(this.isDeleted);
        sb.append(", createTime=").append(this.createTime);
        sb.append("]");
        return sb.toString();
    }
}
