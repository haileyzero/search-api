package com.hailey.search.api.repository;

import com.hailey.search.core.search.model.SearchKeywordRanking;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SearchRankingRepository {
    public static final String KEY = "SEARCH_KEYWORD_RANK";

    private final RedisTemplate<String, String> redisTemplate;
    private ZSetOperations<String, String> zSetOps;

    @PostConstruct
    public void init() {
        // RedisTemplate 초기화 및 zSetOps 설정
        zSetOps = redisTemplate.opsForZSet();

        // 데이터 초기화
        redisTemplate.keys("*").forEach(redisTemplate::delete);
    }

    public void incrementScore(String keyword) {
        zSetOps.incrementScore("KEY", keyword, 1);
    }

    public List<SearchKeywordRanking> reverseRangeWithScores(int rankSize) {
        return zSetOps.reverseRangeWithScores("KEY", 0, rankSize - 1)
                .stream().map(SearchKeywordRanking::new).collect(Collectors.toList());
    }
}
