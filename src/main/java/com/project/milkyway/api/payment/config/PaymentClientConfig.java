package com.project.milkyway.api.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

@Configuration
public class PaymentClientConfig {
    @Value("${payment.base-url}")
    private String BASE_URL;
    @Value("${payment.auth-header-prefix}")
    private String AUTH_HEADER_PREFIX;
    @Value("${payment.secret-key}")
    private String SECRET_KEY;

    @Bean
    public RestClient paymentClient() {
        return RestClient.builder()
                .requestFactory(requestFactory())
                .baseUrl(BASE_URL)
                .defaultHeader("Authorization", createAuthorizationHeader())
                .build();
    }

    private SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(Duration.ofSeconds(3));
        requestFactory.setReadTimeout(Duration.ofSeconds(30));

        return requestFactory;
    }

    private String createAuthorizationHeader() {
        byte[] encodedBytes = Base64.getEncoder().encode((SECRET_KEY + ":").getBytes(StandardCharsets.UTF_8));
        return AUTH_HEADER_PREFIX + " " + new String(encodedBytes);
    }
}
