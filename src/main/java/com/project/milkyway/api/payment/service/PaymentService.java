package com.project.milkyway.api.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.milkyway.api.payment.dto.PaymentCancelRequestDto;
import com.project.milkyway.api.payment.dto.PaymentConfirmRequestDTO;
import com.project.milkyway.api.payment.dto.PaymentResponseDTO;
import com.project.milkyway.api.payment.dto.PaymentErrorResponse;
import com.project.milkyway.api.payment.exception.PaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RestClient paymentClient;
    private final ObjectMapper objectMapper;

    public PaymentResponseDTO confirmPayment(PaymentConfirmRequestDTO confirmRequestDTO) {
        return paymentClient.post()
                .uri("/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .body(confirmRequestDTO)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new PaymentException(response.getStatusCode().value(), objectMapper.readValue(response.getBody(), PaymentErrorResponse.class));
                })
                .body(PaymentResponseDTO.class);
    }

    public PaymentResponseDTO cancelPayment(String paymentKey, PaymentCancelRequestDto cancelRequestDTO) {
        return paymentClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/{paymentKey}/cancel")
                        .build(paymentKey))
                .contentType(MediaType.APPLICATION_JSON)
                .body(cancelRequestDTO)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new PaymentException(response.getStatusCode().value(), objectMapper.readValue(response.getBody(), PaymentErrorResponse.class));
                })
                .body(PaymentResponseDTO.class);
    }

    public PaymentResponseDTO retrievePayment(String paymentKey) {
        return paymentClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{paymentKey}")
                        .build(paymentKey))
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new PaymentException(response.getStatusCode().value(), objectMapper.readValue(response.getBody(), PaymentErrorResponse.class));
                })
                .body(PaymentResponseDTO.class);
    }
}
