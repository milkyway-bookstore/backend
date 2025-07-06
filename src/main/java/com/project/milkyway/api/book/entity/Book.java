package com.project.milkyway.api.book.entity;

import com.project.milkyway.api.member.entity.Member;
import com.project.milkyway.api.member.entity.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="book")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    // 임시로 member id 입력
    private Long pubId;

    private String title;
    private String desc;
    private String author;
    private Integer price;
    private UUID coverImg;
    @Column(columnDefinition = "tinyint")
    private Integer status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Book changeStatus(BookStatus status) {
        return this.toBuilder()
                .status(status.getValue())
                .build();
    }
}
