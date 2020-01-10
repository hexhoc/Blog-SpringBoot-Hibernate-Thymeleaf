package com.hexhoc.springbootblog.tag;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) NOT NULL COMMENT 'tag table primary key id'")
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(100) NOT NULL COMMENT 'tag name'")
    private String name;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(4) NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes'")
    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time'")
    private Date createTime;


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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        sb.append(", tagId=").append(this.id);
        sb.append(", tagName=").append(this.name);
        sb.append(", isDeleted=").append(this.isDeleted);
        sb.append(", createTime=").append(this.createTime);
        sb.append("]");
        return sb.toString();
    }
}
