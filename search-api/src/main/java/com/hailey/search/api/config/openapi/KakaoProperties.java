package com.hailey.search.api.config.openapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakao.api")
@Data
public class KakaoProperties {
    private String url;
    private String uri;
    private String key;
}
