package com.hailey.search.api.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private ServiceErrorCode serviceErrorCode;

    public ServiceException(ServiceErrorCode serviceErrorCode) {
        super(serviceErrorCode.getMessage());
        this.serviceErrorCode = serviceErrorCode;
    }
}
