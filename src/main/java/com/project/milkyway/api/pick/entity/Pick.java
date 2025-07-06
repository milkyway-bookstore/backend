package com.project.milkyway.api.pick.entity;

import com.project.milkyway.api.book.entity.Book;
import com.project.milkyway.api.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="pick")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
}
