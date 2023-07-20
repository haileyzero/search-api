package com.hailey.search.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchKeywordRankingDto {
    private String keyword;
    private int score;
}
