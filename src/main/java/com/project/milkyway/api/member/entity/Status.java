package com.project.milkyway.api.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ALIVE(0),
    SLEEP(1),
    DELETE(2),
    ;

    private final int value;
}