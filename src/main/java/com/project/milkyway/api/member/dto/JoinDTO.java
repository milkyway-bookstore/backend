package com.project.milkyway.api.member.dto;

import com.project.milkyway.api.member.entity.Role;
import com.project.milkyway.api.member.entity.Status;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDTO {
    private String email;
    private String password;
    private String confirmPassword;

    private String provider;
    private String providerId;
    private Role role;
    private Status status;
}
