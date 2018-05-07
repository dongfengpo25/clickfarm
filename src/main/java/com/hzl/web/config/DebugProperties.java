package com.hzl.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "cf", ignoreUnknownFields = true)
@PropertySource("classpath:config/debug.properties")
@Component
public class DebugProperties {
    //上传文件相对路径
    private String uploadFilesPath;
    //上传图片相对路径
    private String uploadPicPath;

    public String getUploadFilesPath() {
        return uploadFilesPath;
    }

    public void setUploadFilesPath(String uploadFilesPath) {
        this.uploadFilesPath = uploadFilesPath;
    }

    public String getUploadPicPath() {
        return uploadPicPath;
    }

    public void setUploadPicPath(String uploadPicPath) {
        this.uploadPicPath = uploadPicPath;
    }
}
