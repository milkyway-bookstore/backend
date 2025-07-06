package com.project.milkyway.api.member.controller;

import com.project.milkyway.api.member.dto.JoinDTO;
import com.project.milkyway.api.member.entity.Role;
import com.project.milkyway.api.member.entity.Status;
import com.project.milkyway.api.member.service.MemberService;
import com.project.milkyway.common.response.ApiResponse;
import com.project.milkyway.common.response.ErrorStatus;
import com.project.milkyway.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "회원 관리 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(@RequestBody JoinDTO joinDTO) {
        log.info("----회원가입 시도----");
        JoinDTO newJoinData = joinDTO.toBuilder()
                .provider("tt")
                .providerId("test")
                .role(Role.MEMBER)
                .status(Status.ALIVE)
                .build();
        if (memberService.saveMember(newJoinData)) {
            log.info("----회원가입 성공----");
            return ApiResponse.success(SuccessStatus.COMMON_SUCCESS);
        } else {
            log.info("----회원가입 실패----");
            return ApiResponse.fail(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }




}
