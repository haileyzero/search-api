package com.hailey.search.core.search.model;

import lombok.*;
import org.springframework.data.redis.core.ZSetOperations;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SearchKeywordRanking {
    private String keyword;
    private Double score;

    public SearchKeywordRanking(ZSetOperations.TypedTuple<String> tuple) {
        this.keyword = tuple.getValue();
        this.score = tuple.getScore();
    }
}
