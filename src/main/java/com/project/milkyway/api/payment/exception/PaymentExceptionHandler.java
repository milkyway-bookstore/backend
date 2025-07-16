package com.project.milkyway.api.payment.exception;

import com.project.milkyway.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler({ PaymentException.class })
    protected ResponseEntity<ApiResponse<Void>> handleTossPaymentException(PaymentException e) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(e.getStatusCode())
                .message(e.getErrorResponse().getMessage())
                .success(false)
                .build();
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }
}
