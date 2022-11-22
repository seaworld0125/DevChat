package com.ntt.app.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.ntt.app.Exception
 * fileName       : ErrorCode
 * author         : Kim
 * date           : 2022-11-22
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_TYPE_VALUE("요청 타입 오류입니다", HttpStatus.BAD_REQUEST, "COMMON"),
    INVALID_INPUT_VALUE("입력 값 오류입니다", HttpStatus.BAD_REQUEST, "COMMON"),
    METHOD_NOT_ALLOWED("지원하지 않는 메서드입니다", HttpStatus.METHOD_NOT_ALLOWED, "COMMON"),
    HANDLE_ACCESS_DENIED("접근이 제한되었습니다", HttpStatus.FORBIDDEN, "COMMON"),
    INTERNAL_SERVER_ERROR("서버 에러: 문의바람", HttpStatus.INTERNAL_SERVER_ERROR, "COMMON"),

    // Member
    MEMBER_NOT_FOUND("존재하지 않는 사용자입니다", HttpStatus.NOT_FOUND, "MEMBER"),
    MEMBER_TAG_UPDATE_FAIL("사용자 태그 업데이트 실패", HttpStatus.BAD_REQUEST, "MEMBER"),
    ;

    private String message;
    private HttpStatus status;
    private String code;
}
