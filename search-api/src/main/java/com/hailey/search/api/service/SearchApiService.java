package com.hailey.search.api.service;

import com.hailey.search.api.dto.BaseResponse;

public interface SearchApiService {
    BaseResponse searchPlace(String keyword) throws Exception;
}
