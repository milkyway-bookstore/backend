package com.project.milkyway.api.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentConfirmRequestDTO {
    @Schema(description = "결제 요청 시 토스 서버에서 발급되는 결제 키 값")
    private String paymentKey;
    @Schema(description = "결제 요청 시 생성한 무작위 값의 주문 번호")
    private String orderId;
    @Schema(description = "총 결제 금액")
    private Integer amount;
}
