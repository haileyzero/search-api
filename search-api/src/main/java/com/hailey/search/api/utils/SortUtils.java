package com.hailey.search.api.utils;

import com.hailey.search.api.dto.NaverSearchPlaceResponse;
import com.hailey.search.api.dto.GetPlaceByKeywordResult;
import com.hailey.search.api.dto.KakaoSearchPlaceResponse;
import com.hailey.search.api.exception.ServiceErrorCode;
import com.hailey.search.api.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SortUtils {
    public List<GetPlaceByKeywordResult> getSortedPlaces(KakaoSearchPlaceResponse kakaoResults, NaverSearchPlaceResponse naverResults) {
        List<GetPlaceByKeywordResult> sortedDocuments = new ArrayList<>();

        kakaoResults.getDocuments().stream()
                .filter(kakaoPlace -> naverResults.getItems().stream()
                        .anyMatch(naverPlace -> isSamePlace(kakaoPlace.getPlaceName(), naverPlace.getTitle())))
                .forEach(kakaoPlace -> {
                    sortedDocuments.add(new GetPlaceByKeywordResult(kakaoPlace.getPlaceName(),
                            kakaoPlace.getAddressName(), kakaoPlace.getRoadAddressName()));
                });

        int remainingCount = 5 - sortedDocuments.size();
        if (remainingCount > 0) {
            kakaoResults.getDocuments().stream()
                    .filter(kakaoPlace -> naverResults.getItems().stream()
                            .noneMatch(naverPlace -> isSamePlace(kakaoPlace.getPlaceName(), naverPlace.getTitle())))
                    .limit(remainingCount)
                    .forEach(kakaoPlace -> {
                        sortedDocuments.add(new GetPlaceByKeywordResult(kakaoPlace.getPlaceName(),
                                kakaoPlace.getAddressName(), kakaoPlace.getRoadAddressName()));
                    });
        }

        int additionalCount = 10 - sortedDocuments.size();
        if (additionalCount > 0) {
            naverResults.getItems().stream()
                    .filter(naverPlace -> kakaoResults.getDocuments().stream()
                            .noneMatch(kakaoPlace -> isSamePlace(kakaoPlace.getPlaceName(), naverPlace.getTitle())))
                    .limit(additionalCount)
                    .forEach(naverPlace -> {
                        sortedDocuments.add(new GetPlaceByKeywordResult(naverPlace.getTitle(),
                                naverPlace.getAddress(), naverPlace.getRoadAddress()));
                    });
        }

        if(sortedDocuments == null || sortedDocuments.size() == 0) {
            throw new ServiceException(ServiceErrorCode.GET_PLACE_ERROR);
        }

        return sortedDocuments;
    }

    /**
     * 업체명 공백 제거 비교, 태그 제거 비교, 문자열 유사도 비교, 장소 위치 비교 등
     * @return
     */
    public Boolean isSamePlace(String kakaoTitle, String naverTitle) {
        double similarity = calculateStringSimilarity(kakaoTitle, naverTitle);
        return similarity >= 0.85; // 예시로 0.8 이상의 유사도를 만족하는지 확인
    }

    public static double calculateStringSimilarity(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0.0;
        }

        // 공백 제거
        String normalizedStr1 = str1.replaceAll("\\s", "");
        String normalizedStr2 = str2.replaceAll("\\s", "");

        LevenshteinDistance distance = new LevenshteinDistance();
        int str1Length = normalizedStr1.length();
        int str2Length = normalizedStr2.length();
        int maxDistance = Math.max(str1Length, str2Length);

        int levenshteinDistance = distance.apply(normalizedStr1, normalizedStr2);
        double similarity = (maxDistance - levenshteinDistance) / (double) maxDistance;

        return similarity;
    }
}
