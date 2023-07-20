package com.hailey.search.api.service.impl;

import com.hailey.search.api.config.openapi.NaverProperties;
import com.hailey.search.api.dto.BaseResponse;
import com.hailey.search.api.dto.NaverSearchPlaceResponse;
import com.hailey.search.api.service.SearchApiService;
import com.hailey.search.api.utils.SearchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSearchApiService implements SearchApiService {
    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;
    private final SearchUtils searchUtils;

    @Override
    public BaseResponse<NaverSearchPlaceResponse> searchPlace(String keyword) throws Exception {
        String httpUrl = String.format("%s%s", naverProperties.getUrl(), naverProperties.getUri());

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", naverProperties.getId());
        headers.add("X-Naver-Client-Secret", naverProperties.getSecret());

        var response = callNaverApi(httpUrl, keyword, headers);

        var responseBody = response.getBody();

        // 각 아이템의 제목에서 HTML 태그 제거
        responseBody.getItems().forEach(item ->
                item.setTitle(searchUtils.tagRemove(item.getTitle())));

        //throw new Exception();

        return BaseResponse.<NaverSearchPlaceResponse>builder()
                .item(responseBody)
                .build();
    }

    private ResponseEntity<NaverSearchPlaceResponse> callNaverApi(String httpUrl, String keyword, HttpHeaders headers) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                .queryParam("query", keyword)
                .queryParam("display", 5) // max=5
                .queryParam("sort", "random")
                .build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), NaverSearchPlaceResponse.class);
    }
}