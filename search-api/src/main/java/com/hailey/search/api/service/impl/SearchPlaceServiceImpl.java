package com.hailey.search.api.service.impl;

import com.hailey.search.api.dto.*;
import com.hailey.search.api.service.SearchPlaceService;
import com.hailey.search.api.service.SearchRankingService;
import com.hailey.search.api.utils.SortUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hailey.search.api.exception.ServiceErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlaceServiceImpl implements SearchPlaceService {
    private final SearchRankingService searchRankingService;
    private final KakaoSearchApiService kakaoSearchApiService;
    private final NaverSearchApiService naverSearchApiService;
    private final SortUtils sortUtils;

    @Override
    public PageResponse<GetPlaceByKeywordResult> searchPlaceByKeyword(String keyword) {
        // 1. KakaoAPI
        KakaoSearchPlaceResponse kakaoResults = null;
        try {
            kakaoResults = kakaoSearchApiService.searchPlace(keyword).getItem();
        } catch (Exception ex) {
            //not throw
            log.error("status {}, message: {}", KAKAO_API_ERROR.getStatus(), KAKAO_API_ERROR.getMessage());
            kakaoResults = new KakaoSearchPlaceResponse();
            kakaoResults.setDocuments(new ArrayList<>());
        }

        // 2. NaverAPI
        NaverSearchPlaceResponse naverResults = null;
        try {
            naverResults = naverSearchApiService.searchPlace(keyword).getItem();
        } catch (Exception ex) {
            //not throw
            log.error("status {}, message: {}", NAVER_API_ERROR.getStatus(), NAVER_API_ERROR.getMessage());
            naverResults = new NaverSearchPlaceResponse();
            naverResults.setItems(new ArrayList<>());
        }

        List<GetPlaceByKeywordResult> sortedDocuments = sortUtils.getSortedPlaces(kakaoResults, naverResults);

        try {
            searchRankingService.incrementScore(keyword);
        } catch (Exception ex) {
            log.error("status {}, message: {}", SET_RANK_ERROR.getStatus(), SET_RANK_ERROR.getMessage());
        }

        return PageResponse.<GetPlaceByKeywordResult>builder()
                .items(sortedDocuments)
                .page(PageResponse.Page.builder()
                        .totalCount(sortedDocuments.size())
                        //.size()
                        //.page()
                        .build())
                .build();
    }
}