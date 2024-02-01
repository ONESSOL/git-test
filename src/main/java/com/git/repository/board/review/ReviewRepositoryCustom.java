package com.git.repository.board.review;

import com.git.domain.board.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<Review> findByTitleAndContents(String search, Pageable pageable);
}
