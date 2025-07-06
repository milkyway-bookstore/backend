package com.project.milkyway.api.member.service;

import com.project.milkyway.api.member.dto.JoinDTO;
import com.project.milkyway.api.member.entity.Member;
import com.project.milkyway.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Boolean saveMember(JoinDTO joinDTO) {
        try {
            Member newMember = Member.builder()
                    .email(joinDTO.getEmail())
                    .password(joinDTO.getPassword())
                    .provider(joinDTO.getProvider())
                    .providerId(joinDTO.getProviderId())
                    .status(joinDTO.getStatus().getValue())
                    .role(joinDTO.getRole().getValue())
                    .build();
            memberRepository.save(newMember);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
