package com.hailey.search.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T> {
    private List<T> items;

    private Page page;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Page {
        @Schema(description = "총 개수")
        private Integer totalCount;

        /*@Schema(description = "페이지 별 조회 객체 수")
        private Integer size;

        @Schema(description = "현재 페이지 번호")
        private Integer page;*/
    }
}
