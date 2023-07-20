package com.hailey.search.api.interceptor;

import com.hailey.search.api.exception.ServiceException;
import com.hailey.search.api.exception.ServiceExceptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ServiceExceptionEntity> exceptionHandler(HttpServletRequest request, final ServiceException e) {
        log.error("errorCode: {}, url {}, message: {}",
                e.getServiceErrorCode(), request.getRequestURI(), e.getMessage());
        return ResponseEntity
                .status(e.getServiceErrorCode().getStatus())
                .body(ServiceExceptionEntity.builder()
                        .errorCode(e.getServiceErrorCode())
                        .errorMessage(e.getMessage())
                        .build());
    }


}
