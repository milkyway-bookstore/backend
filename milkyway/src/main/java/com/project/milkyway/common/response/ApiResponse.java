package com.project.milkyway.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final int status;
    private final String message;
    private final boolean success;
    private final T data;

    public static ResponseEntity<ApiResponse<Void>> success(SuccessStatus status) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(status.getStatusCode())
                .message(status.getMessage())
                .success(true)
                .build();
        return ResponseEntity.status(status.getStatusCode()).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(SuccessStatus status, T data) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(status.getStatusCode())
                .message(status.getMessage())
                .success(true)
                .data(data)
                .build();
        return ResponseEntity.status(status.getStatusCode()).body(response);
    }

    public static ResponseEntity<ApiResponse<Void>> fail(ErrorStatus status) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(status.getStatusCode())
                .message(status.getMessage())
                .success(false)
                .build();
        return ResponseEntity.status(status.getStatusCode()).body(response);
    }
}
