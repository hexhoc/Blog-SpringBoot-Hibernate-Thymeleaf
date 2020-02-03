package com.hexhoc.springbootblog.comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key id'")
    private Long id;

    @Column(name = "article_id", columnDefinition = "BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'Associated blog primary key'")
    private Long articleId;

    @Column(name = "commentator", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'Commentator name'")
    private String commentator;

    @Column(name = "email", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'commenters mailbox'")
    private String email;

    @Column(name = "website_url", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'URL'")
    private String websiteUrl;

    @Column(name = "body", columnDefinition = "VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'Comment content'")
    private String body;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Comment submission time'")
    private LocalDateTime createTime;

    @Column(name = "commentator_ip", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'IP address when commenting'")
    private String commentatorIp;

    @Column(name = "reply_body", columnDefinition = "VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'reply content'")
    private String replyBody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "reply_create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'reply time'")
    private LocalDateTime replyCreateTime;

    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0' COMMENT 'Whether the audit passed 0-unaudited 1-approved'")
    private Boolean status;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT '0' COMMENT 'Whether to delete 0-not deleted 1-deleted'")
    private Boolean isDeleted;


    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////


    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCommentatorIp() {
        return commentatorIp;
    }

    public void setCommentatorIp(String commentatorIp) {
        this.commentatorIp = commentatorIp;
    }

    public String getReplyBody() {
        return replyBody;
    }

    public void setReplyBody(String replyBody) {
        this.replyBody = replyBody;
    }

    public LocalDateTime getReplyCreateTime() {
        return replyCreateTime;
    }

    public void setReplyCreateTime(LocalDateTime replyCreateTime) {
        this.replyCreateTime = replyCreateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
