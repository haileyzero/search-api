package com.hailey.search.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceErrorCode {
    /** kakao */
    KAKAO_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "카카오 API 조회에 실패했습니다."),

    /** naver */
    NAVER_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "네이버 API 조회에 실패했습니다."),

    /** common */
    GET_PLACE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "장소 조회에 실패했습니다."),
    SET_RANK_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "키워드 목록 갱신에 실패했습니다.");


    private final HttpStatus status;
    private String message;

    ServiceErrorCode(HttpStatus status) {
        this.status = status;
    }

    ServiceErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
