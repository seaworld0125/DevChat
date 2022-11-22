package com.ntt.app.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

/**
 * packageName    : com.ntt.app.Exception
 * fileName       : GlobalExceptionHandler
 * author         : Kim
 * date           : 2022-11-22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    }

    /**
     * ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleBindException(BindException e) {
        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorResponse.of(e);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        return ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
    }

//    ------------------------------------------    //

    @ExceptionHandler(BusinessException.class)
    protected ErrorResponse handleBusinessException(final BusinessException e) {
        return ErrorResponse.of(e.getErrorCode());
    }

    @ExceptionHandler(DataAccessException.class)
    protected ErrorResponse handleDataAccessException(final DataAccessException e) {
        return ErrorResponse.newSQLErrorResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        log.info(e.getMessage());
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
