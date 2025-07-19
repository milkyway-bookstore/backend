package com.project.milkyway.api.order.entity;

import com.project.milkyway.api.delivery.entity.Delivery;
import com.project.milkyway.api.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="order")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private Integer purchasePrice;
    private String paymentKey;

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

}
