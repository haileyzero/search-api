package com.hailey.search.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class KakaoSearchPlaceResponse {
    @Schema(description = "응답 관련 정보")
    @JsonProperty("meta")
    private Meta meta;

    @Schema(description = "응답 결과")
    @JsonProperty("documents")
    private List<Document> documents;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Meta {
        @Schema(description ="검색어에 검색된 문서 수")
        @JsonProperty("total_count")
        private Integer totalCount;

        @Schema(description ="totalCount 중 노출 가능 문서 수")
        @Size(max = 45)
        @JsonProperty("pageable_count")
        private Integer pageableCount;

        @Schema(description ="현재 페이지가 마지막 페이지인지 여부이며 False면 다음 요청 시 page값을 증가시켜 다음 페이지 요청가능")
        @JsonProperty("is_end")
        private Boolean isEnd;

        @Schema(description ="질의어 지역 및 키워드 분석 정보")
        @JsonProperty("same_name")
        private SameName sameName;

        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Data
        public static class SameName {
            @Schema(description = "질의어에서 인식된 지역의 리스트")
            private List<String> region;

            @Schema(description = "질의어에서 지역 정보를 제외한 키워드")
            private String keyword;

            @Schema(description = "인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보")
            @JsonProperty("selected_region")
            private String selectedRegion;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Document {
        @Schema(description = "장소 ID")
        private String id;

        @Schema(description = "장소명, 업체명")
        @JsonProperty("place_name")
        private String placeName;

        @Schema(description = "카테고리 이름")
        @JsonProperty("category_name")
        private String categoryName;

        @Schema(description = "중요 카테고리만 그룹핑한 카테고리 그룹 코드")
        @JsonProperty("category_group_code")
        private String categoryGroupCode;

        @Schema(description = "중요 카테고리만 그룹핑한 카테고리 그룹명")
        @JsonProperty("category_group_name")
        private String categoryGroupName;

        @Schema(description = "전화번호")
        private String phone;

        @Schema(description = "전체 지번 주소")
        @JsonProperty("address_name")
        private String addressName;

        @Schema(description = "전체 도로명 주소")
        @JsonProperty("road_address_name")
        private String roadAddressName;

        @Schema(description = "X 좌표값, 경위도인 경우 경도")
        private String x;

        @Schema(description = "Y 좌표값, 경위도인 경우 위도")
        private String y;

        @Schema(description = "장소 상세페이지 URL")
        @JsonProperty("place_url")
        private String placeUrl;

        @Schema(description = "중심좌표까지의 거리(단, x,y 파라미터를 준 경우에만 존재)")
        private String distance;
    }
}
