package com.project.milkyway.api.payment.exception;

import com.project.milkyway.api.payment.dto.PaymentErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentException extends RuntimeException{
    private final int statusCode;
    private final PaymentErrorResponse errorResponse;
}
