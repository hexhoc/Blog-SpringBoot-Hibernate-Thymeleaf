package com.hexhoc.springbootblog.config;

import java.util.Map;

public interface ConfigService {

    /**
     * Modify configuration items
     *
     * @param configName
     * @param configValue
     * @return
     */
    int updateConfig(String configName, String configValue);

    /**
     * Get all configuration items
     *
     * @return
     */
    Map<String, String> getAllConfigs();
}

