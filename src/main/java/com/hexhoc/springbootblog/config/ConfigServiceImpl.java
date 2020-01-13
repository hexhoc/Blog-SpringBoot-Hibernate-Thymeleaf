package com.hexhoc.springbootblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    private ConfigRepository configRepository;

    //DEFAULT CONFIG VALUES
    public static final String websiteName = "personal blog";
    public static final String websiteDescription = "personal blog SpringBoot2+Thymeleaf+Hibernate";
    public static final String websiteLogo = "/admin/dist/img/logo2.png";
    public static final String websiteIcon = "/admin/dist/img/favicon.png";
    public static final String yourAvatar = "/admin/dist/img/13.png";
    public static final String yourEmail = "mymail@mail.com";
    public static final String yourName = "my name";
    public static final String footerAbout = "your personal blog. have fun.";
    public static final String footerCopyRight = "@2020";
    public static final String footerPoweredBy = "personal blog";
    public static final String footerPoweredByURL = "##";


    @Autowired
    public ConfigServiceImpl(ConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    @Override
    public int updateConfig(String configName, String configValue) {
        Optional<Config> blogConfig = configRepository.findById(configName);
        if (!blogConfig.isEmpty()) {
            Config config = blogConfig.get();
            config.setConfigValue(configValue);
            config.setUpdateTime(new Date());
            configRepository.save(config);
            return 1;
        }
        return 0;
    }

    @Override
    public Map<String, String> getAllConfigs() {
        //Get all config records and encapsulate them as maps
        List<Config> blogConfigs = configRepository.findAll();
        Map<String, String> configMap = blogConfigs.stream().collect(Collectors.toMap(Config::getConfigName, Config::getConfigValue));

        fillDefaultValues(configMap);

        return configMap;
    }

    /**
     * Fill config map with default values if it empty
     */
    private void fillDefaultValues(Map<String, String> configMap){

        for (Map.Entry<String, String> config : configMap.entrySet()) {
            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteName);
            }
            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteDescription);
            }
            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteLogo);
            }
            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteIcon);
            }
            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourAvatar);
            }
            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourEmail);
            }
            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourName);
            }
            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerAbout);
            }
            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerCopyRight);
            }
            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredBy);
            }
            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredByURL);
            }
        }
    }
}
