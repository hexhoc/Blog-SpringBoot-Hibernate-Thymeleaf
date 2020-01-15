package com.hexhoc.springbootblog.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "config")
public class Config {

    @Id
    @Column(name = "config_name", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Name of the configuration item'")
    private String configName;

    @Column(name = "config_value", columnDefinition = "VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'Configuration item value'")
    private String configValue;

    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time'")
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modification time'")
    private LocalDateTime updateTime;

    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
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
}
