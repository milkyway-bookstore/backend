package com.project.milkyway.api.order.entity;

import com.project.milkyway.api.book.entity.Book;
import com.project.milkyway.api.delivery.entity.Delivery;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="order_detail")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private Integer bookCnt;
    private Integer purchasePrice;


    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
}
