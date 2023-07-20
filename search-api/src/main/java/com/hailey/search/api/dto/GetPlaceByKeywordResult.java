package com.hailey.search.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetPlaceByKeywordResult {
    @Schema(description = "장소명")
    private String placeName;

    @Schema(description = "전체 지번 주소")
    private String addressName;

    @Schema(description = "전체 도로명 주소")
    private String roadAddressName;
}
