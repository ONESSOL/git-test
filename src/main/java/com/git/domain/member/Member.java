package com.git.domain.member;

import com.git.domain.BaseTimeEntity;
import com.git.domain.board.review.Review;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;

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

    @Builder
    public Member(String username, String password, String name, String phoneNum, String email, Address address, Role role, SocialType socialType, String socialId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
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
}






















