package com.git.domain.member;

import com.git.domain.BaseTimeEntity;
import com.git.domain.board.review.Review;
import com.git.domain.cart.Cart;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNum;
    private String email;
    @Embedded
    private Address address;
    @Enumerated(STRING)
    private Role role;
    @Enumerated(STRING)
    private SocialType socialType;
    private String socialId;
    @OneToMany(mappedBy = "member", cascade = REMOVE, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder
    public Member(String username, String password, String name, String phoneNum, String email, Address address, Role role, SocialType socialType, String socialId, Cart cart) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
        createCart(cart);
    }

    protected Member() {
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void update(String phoneNum, String email, Address address) {
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
    }

    public void createCart(Cart cart) {
        this.cart = cart;
        cart.createMember(this);
    }
}






















