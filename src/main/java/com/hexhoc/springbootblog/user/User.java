package com.hexhoc.springbootblog.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", columnDefinition = "BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'user table primary key id'")
    private Long id;
    @Column(name = "username", columnDefinition = "VARCHAR(25) UNIQUE NOT NULL COMMENT 'Name that uses for login'")
    private String username;
    @Column(name = "password", columnDefinition = "VARCHAR(25) NOT NULL COMMENT 'Password that uses for login'")
    private String password;
    @Column(name = "nickname", columnDefinition = "VARCHAR(25) NOT NULL COMMENT 'Name that uses for display'")
    private String nickname;
    @Column(name = "is_locked", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0' COMMENT 'Prevents the user from logging in'")
    private Boolean isLocked;


    /**
     * GETTER AND SETTER
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", username=").append(this.username);
        sb.append(", password=").append(this.password);
        sb.append(", nickName=").append(this.nickname);
        sb.append(", isLocked=").append(this.isLocked);
        sb.append("]");
        return sb.toString();
    }
}
