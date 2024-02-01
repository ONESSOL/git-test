package com.git.response.board.review;

import com.git.domain.board.review.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewListResponse {

    private Long id;
    private String title;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private int hits;

    public static ReviewListResponse toSave(Review review) {
        ReviewListResponse response = new ReviewListResponse();
        response.setId(review.getId());
        response.setTitle(review.getTitle());
        response.setCreatedBy(review.getCreatedBy());
        response.setUpdatedBy(review.getUpdatedBy());
        response.setCreatedTime(review.getCreatedTime());
        response.setUpdatedTime(review.getUpdatedTime());
        response.setHits(review.getHits());
        return response;
    }
}
