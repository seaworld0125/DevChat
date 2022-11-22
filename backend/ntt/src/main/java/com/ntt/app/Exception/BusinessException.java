package com.ntt.app.Exception;

import lombok.Getter;

/**
 * packageName    : com.ntt.app.Exception
 * fileName       : BusinessException
 * author         : Kim
 * date           : 2022-11-18
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode code) {
        super(code.getMessage());
        this.errorCode = code;
    }
}
