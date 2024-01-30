package com.git.repository.board.review;

import com.git.domain.board.review.ReviewFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFileRepository extends JpaRepository<ReviewFileEntity, Long> {
}
