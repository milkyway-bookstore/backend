package com.project.milkyway.api.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResponseDTO {
    private String orderId;
    private String orderName;
    private String method;
    private String currency;
    private int totalAmount;
    private int balanceAmount;
    private String status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date requestedAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date approvedAt;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<CancelInfo> cancels;

    @Getter
    public static class CancelInfo {
        private int cancelAmount;
        private String cancelReason;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Date canceledAt;
        private String cancelStatus;
        private int refundableAmount;
    }
}
