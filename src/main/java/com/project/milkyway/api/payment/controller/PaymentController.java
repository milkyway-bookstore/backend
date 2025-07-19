package com.project.milkyway.api.payment.controller;

import com.project.milkyway.api.payment.dto.PaymentCancelRequestDto;
import com.project.milkyway.api.payment.dto.PaymentConfirmRequestDTO;
import com.project.milkyway.api.payment.dto.PaymentResponseDTO;
import com.project.milkyway.api.payment.dto.PaymentTempInfoRequestDTO;
import com.project.milkyway.api.payment.service.PaymentService;
import com.project.milkyway.common.response.ApiResponse;
import com.project.milkyway.common.response.ErrorStatus;
import com.project.milkyway.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@Tag(name = "Payment", description = "결제 API")
@CrossOrigin(origins = "http://localhost:4000",
        allowCredentials = "true") // 임시 - 테스트용 토스 샘플페이지
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "결제 정보 임시 저장", description = "결제 승인 요청 전 주문 ID와 결제값을 서버에 임시로 저장합니다.")
    @PostMapping("/temp-info")
    public ResponseEntity<ApiResponse<Void>> saveTempInfo(HttpSession session, @RequestBody PaymentTempInfoRequestDTO tempInfoRequestDTO) {
        if(StringUtils.hasText(tempInfoRequestDTO.getOrderId()) && tempInfoRequestDTO.getAmount() != null) {
            session.setAttribute(tempInfoRequestDTO.getOrderId(), tempInfoRequestDTO.getAmount());
            return ApiResponse.success(SuccessStatus.COMMON_SUCCESS);
        }else {
            return ApiResponse.fail(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }

    @Operation(summary = "결제 승인", description = "결제 정보를 검증하고 결제 승인을 요청합니다.")
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(HttpSession session, @RequestBody PaymentConfirmRequestDTO confirmRequestDTO) {
        Integer tempAmount = (Integer) session.getAttribute(confirmRequestDTO.getOrderId());

        if(tempAmount != null && tempAmount.equals(confirmRequestDTO.getAmount())) {
            PaymentResponseDTO responseDTO = paymentService.confirmPayment(confirmRequestDTO);
            session.removeAttribute(confirmRequestDTO.getOrderId());
            return ApiResponse.success(SuccessStatus.COMMON_SUCCESS, responseDTO);
        }else {
            return ApiResponse.fail(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }

    @Operation(summary = "결제 취소", description = "결제키를 사용하여 기존 결제를 취소합니다.")
    @PostMapping("/{paymentKey}/cancel")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> cancelPayment(@PathVariable String paymentKey, @RequestBody PaymentCancelRequestDto requestDTO) {
        PaymentResponseDTO responseDTO = paymentService.cancelPayment(paymentKey, requestDTO);

        return ApiResponse.success(SuccessStatus.COMMON_SUCCESS, responseDTO);
    }

    @Operation(summary = "결제 조회", description = "결제키를 사용하여 기존 결제 내역을 조회합니다.")
    @GetMapping("/{paymentKey}/retrieve")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> retrievePayment(@PathVariable String paymentKey) {
        PaymentResponseDTO responseDTO = paymentService.retrievePayment(paymentKey);

        return ApiResponse.success(SuccessStatus.COMMON_SUCCESS, responseDTO);
    }
}
