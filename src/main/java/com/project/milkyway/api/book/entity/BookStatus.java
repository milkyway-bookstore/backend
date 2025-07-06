package com.project.milkyway.api.book.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {

    TBU(0), // To Be Update
    PUB(1), // 출판
    DEL(2), // 삭제

    ;

    private final Integer value;
}
