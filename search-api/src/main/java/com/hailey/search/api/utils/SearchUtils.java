package com.hailey.search.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class SearchUtils {
    public static String tagRemove(String str) {
        // HTML 태그 제거를 위한 정규식 패턴
        Pattern htmlTagPattern = Pattern.compile("<[^>]*>");

        return htmlTagPattern.matcher(str).replaceAll("");
    }
}
