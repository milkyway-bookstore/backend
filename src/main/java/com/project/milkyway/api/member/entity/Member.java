package com.project.milkyway.api.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;
    private String password;

    @Column(length = 2, columnDefinition = "char(2)")
    private String provider;
    @Column(length = 63)
    private String providerId;
    @Column(length = 1, columnDefinition = "char(1)")
    private Integer role;
    @Column(columnDefinition = "tinyint")
    private Integer status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Member changeStatus(Status status) {
        return this.toBuilder()
                .status(status.getValue())
                .build();
    }

}
