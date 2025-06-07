package com.project.milkyway.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorStatus {

    // COMMON STATUS
    COMMON_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    COMMON_NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found"),
    COMMON_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    COMMON_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return httpStatus.value();
    }

}
