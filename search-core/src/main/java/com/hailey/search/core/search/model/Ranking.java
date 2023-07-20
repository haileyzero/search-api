package com.hailey.search.core.search.model;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Ranking {
    private String keyword;

    @Builder.Default
    @Setter
    private Integer score = 0;
}
