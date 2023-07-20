package com.hailey.search.api.service.impl;

import com.hailey.search.api.config.openapi.KakaoProperties;
import com.hailey.search.api.dto.BaseResponse;
import com.hailey.search.api.dto.KakaoSearchPlaceResponse;
import com.hailey.search.api.service.SearchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSearchApiService implements SearchApiService {
    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    @Override
    public BaseResponse<KakaoSearchPlaceResponse> searchPlace(String keyword) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoProperties.getKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String httpUrl = String.format("%s%s", kakaoProperties.getUrl(), kakaoProperties.getUri());
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                .queryParam("query", keyword)
                .queryParam("size", 10)
                .build();

        var responseBody = this.callKakaoApi(builder.toString(), entity);

        //throw new Exception();

        return BaseResponse.<KakaoSearchPlaceResponse>builder()
                .item(responseBody)
                .build();
    }

    private KakaoSearchPlaceResponse callKakaoApi(String url, HttpEntity<String> entity) {
        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoSearchPlaceResponse.class);
            return response.getBody();
        } catch (Exception ex) {
            log.info("Error occurred while calling Kakao API: {}", ex.getMessage());
            return new KakaoSearchPlaceResponse();
        }
    }
}