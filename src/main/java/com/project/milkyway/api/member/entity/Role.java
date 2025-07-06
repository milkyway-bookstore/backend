package com.project.milkyway.api.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN   ("SERVER_ADMIN",     0),
    MANAGER ("SERVICE_MANAGER",  1),
    SELLER  ("BOOK_SELLER",      2),
    MEMBER  ("REGISTER",         3),
    VIEWER  ("GUEST",            4),

    ;

    private final String key;
    private final Integer value;
}
