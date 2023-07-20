package com.hailey.search.api.controller;

import com.hailey.search.api.dto.GetPlaceByKeywordResult;
import com.hailey.search.api.dto.PageResponse;
import com.hailey.search.api.service.SearchPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SearchPlaceController")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchPlaceController {
    private final SearchPlaceService service;

    @Operation(summary = "장소 키워드 검색")
    @GetMapping
    public PageResponse<GetPlaceByKeywordResult> searchPlaceByKeyword(@RequestParam String keyword) {
        return service.searchPlaceByKeyword(keyword);
    }
}
