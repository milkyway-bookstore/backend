package com.project.milkyway.api.book.entity;

import com.project.milkyway.api.cart.entity.Cart;
import com.project.milkyway.api.order.entity.OrderDetail;
import com.project.milkyway.api.pick.entity.Pick;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
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
    private String coverImg;
    @Column(columnDefinition = "tinyint")
    private Integer status;

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    @OneToMany(mappedBy = "book", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Cart> cart;

    @OneToMany(mappedBy = "book", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Pick> pick;

    @OneToMany(mappedBy = "book", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    public Book changeStatus(BookStatus status) {
        return this.toBuilder()
                .status(status.getValue())
                .build();
    }
}
