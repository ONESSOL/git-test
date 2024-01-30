package com.git.domain.board.review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class ReviewFileEntity {

    @Id @GeneratedValue
    @Column(name = "review_file_id")
    private Long id;
    private String originalFileName;
    private String storedFileName;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewFileEntity(String originalFileName, String storedFileName, Review review) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        createReview(review);
    }

    protected ReviewFileEntity() {
    }

    public void createReview(Review review) {
        this.review = review;
        review.addBoardFile(this);
    }
}
