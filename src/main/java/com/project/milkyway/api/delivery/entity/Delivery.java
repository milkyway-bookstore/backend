package com.project.milkyway.api.delivery.entity;

import com.project.milkyway.api.member.entity.Member;
import com.project.milkyway.api.order.entity.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="delivery")
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String deliveryName;

    @Column(columnDefinition = "tinyint")
    private Integer defaultStatus;

    private String name;
    private String phone;
    private String addr;
    private String addrDtl;
    private String zipcode;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Order> order;

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

}
