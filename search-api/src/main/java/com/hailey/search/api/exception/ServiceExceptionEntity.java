package com.hailey.search.api.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceExceptionEntity {
    private ServiceErrorCode errorCode;
    private String errorMessage;
}
