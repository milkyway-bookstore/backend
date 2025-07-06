package com.project.milkyway.api.healthCheck.controller;

import com.project.milkyway.common.response.ApiResponse;
import com.project.milkyway.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "서버 상태 API")
@RestController
public class healthCheckController {

    @Operation(
            summary = "서버 상태 체크 API",
            description = "서버 상태 체크"
    )
    @GetMapping("/ping")
    public ResponseEntity<ApiResponse<Void>> ping() {
        return ApiResponse.success(SuccessStatus.SERVER_STATUS_OK);
    }
}
