package com.git.domain.board.review;

import com.git.domain.BaseEntity;
import com.git.domain.board.notice.NoticeFileEntity;
import com.git.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Review extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;
    private String title;
    private String contents;
    private int fileAttached;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "review", cascade = REMOVE, orphanRemoval = true)
    private List<ReviewFileEntity> boardFileEntities = new ArrayList<>();

    @Builder
    public Review(String title, String contents, int fileAttached, Member member) {
        this.title = title;
        this.contents = contents;
        this.fileAttached = fileAttached;
        createMember(member);
    }

    protected Review() {
    }

    public void createMember(Member member) {
        this.member = member;
        member.addReview(this);
    }

    public void addBoardFile(ReviewFileEntity reviewFileEntity) {
        this.boardFileEntities.add(reviewFileEntity);
    }
}
