package com.project.milkyway.api.member.entity;

import com.project.milkyway.api.cart.entity.Cart;
import com.project.milkyway.api.delivery.entity.Delivery;
import com.project.milkyway.api.order.entity.Order;
import com.project.milkyway.api.pick.entity.Pick;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Cart> cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Delivery> delivery;

    @OneToMany(mappedBy = "member", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Pick> pick;

    @OneToMany(mappedBy = "member", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Order> order;

    public Member changeStatus(Status status) {
        return this.toBuilder()
                .status(status.getValue())
                .build();
    }

}
