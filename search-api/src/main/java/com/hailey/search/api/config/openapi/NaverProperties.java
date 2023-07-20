package com.hailey.search.api.config.openapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "naver.api")
@Data
public class NaverProperties {
    private String url;
    private String uri;
    private String id;
    private String secret;
}
