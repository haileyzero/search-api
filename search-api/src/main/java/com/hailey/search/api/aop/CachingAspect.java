package com.hailey.search.api.aop;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.hailey.search.api.dto.GetPlaceByKeywordResult;
import com.hailey.search.api.dto.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class CachingAspect {
    private final Cache<String, PageResponse<GetPlaceByKeywordResult>> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();

    @Around("execution(* com.hailey.search.api.service.SearchPlaceService.searchPlaceByKeyword(..)) && args(keyword)")
    public PageResponse<GetPlaceByKeywordResult> getCachedValue(ProceedingJoinPoint proceedingJoinPoint, String keyword) throws Throwable {
        PageResponse<GetPlaceByKeywordResult> cacheResult = cache.getIfPresent(keyword);
        if(cacheResult != null) {
            log.info("CacheResult: {}", cacheResult);
            return cacheResult;
        }

        PageResponse<GetPlaceByKeywordResult> result = (PageResponse<GetPlaceByKeywordResult>)proceedingJoinPoint.proceed();
        cache.put(keyword, result);
        log.info("successCaching: {}", result);

        return result;
    }
}
