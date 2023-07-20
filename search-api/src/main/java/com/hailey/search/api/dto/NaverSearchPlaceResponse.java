package com.hailey.search.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class NaverSearchPlaceResponse {
    @Schema(description = "개별 검색 결과")
    private List<Item> items;

    @Schema(description = "검색 결과를 생성한 시간")
    private String lastBuildDate;

    @Schema(description = "한 번에 표시할 검색 결과 개수")
    private Integer display;

    @Schema(description = "총 검색 결과 개수")
    private Integer total;

    @Schema(description = "검색 시작 위치")
    private Integer start;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Item {
        @Schema(description = "업체, 기관의 이름")
        private String title;

        @Schema(description = "업체, 기관의 상세 정보 URL")
        private String link;

        @Schema(description = "업체, 기관의 분류 정보")
        private String category;

        @Schema(description = "업체, 기관에 대한 설명")
        private String description;

        @Schema(description = "값을 반환하지 않는 요소(하위 호환성을 유지하기 위해 있는 요소)")
        private String telephone;

        @Schema(description = "업체, 기관명의 지번 주소")
        private String address;

        @Schema(description = "업체, 기관명의 도로명 주소")
        private String roadAddress;

        @Schema(description = "업체, 기관이 위치한 장소의 x 좌표")
        private Integer mapx;

        @Schema(description = "업체, 기관이 위치한 장소의 y 좌표")
        private Integer mapy;
    }
}