package com.wendel.test.runTheBank.domain.validator;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public ApiException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        httpStatus = HttpStatus.BAD_REQUEST;
        message = exceptionMessage.getMessage();
    }

    protected ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}