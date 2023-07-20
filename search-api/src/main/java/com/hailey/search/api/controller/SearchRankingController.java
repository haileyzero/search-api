package com.hailey.search.api.controller;

import com.hailey.search.api.dto.PageResponse;
import com.hailey.search.api.service.SearchRankingService;
import com.hailey.search.api.dto.SearchKeywordRankingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SearchRankingController")
@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class SearchRankingController {
    private final SearchRankingService searchRankingService;

    @Operation(summary = "키워드 검색 순위")
    @GetMapping
    public PageResponse<SearchKeywordRankingDto> SearchRankList() {
        return searchRankingService.reverseRangeWithScores(10);
    }
}
