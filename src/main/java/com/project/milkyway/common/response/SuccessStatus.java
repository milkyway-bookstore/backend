package com.project.milkyway.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {

    // COMMON STATUS
    COMMON_SUCCESS(HttpStatus.OK, "Success"),

    // 200 OK
    SERVER_STATUS_OK(HttpStatus.OK, "Server Status OK"),
    ;

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
