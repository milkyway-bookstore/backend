package com.project.milkyway.api.payment.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentCancelRequestDto {
    private String cancelReason;
    private Integer cancelAmount;
}
