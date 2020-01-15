package com.hexhoc.springbootblog.category;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) NOT NULL COMMENT 'category table primary key'")
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL COMMENT 'category name'")
    private String name;

    @Column(name = "icon", columnDefinition = "VARCHAR(50) NOT NULL COMMENT 'create time' COMMENT 'category icon'")
    private String icon;

    @Column(name = "grade", columnDefinition = "INT(11) NOT NULL DEFAULT '1' COMMENT 'The sort value of the category, the more used, the larger the value'")
    private Integer grade;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time'")
    private LocalDateTime createTime;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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
        sb.append(", categoryId=").append(this.id);
        sb.append(", categoryName=").append(this.name);
        sb.append(", categoryIcon=").append(this.icon);
        sb.append(", categoryRank=").append(this.grade);
        sb.append(", isDeleted=").append(this.isDeleted);
        sb.append(", createTime=").append(this.createTime);
        sb.append("]");
        return sb.toString();
    }}
