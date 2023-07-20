package com.hailey.search.api.service;

import com.hailey.search.api.dto.GetPlaceByKeywordResult;
import com.hailey.search.api.dto.PageResponse;

public interface SearchPlaceService {
    PageResponse<GetPlaceByKeywordResult> searchPlaceByKeyword(String keyword);
}
