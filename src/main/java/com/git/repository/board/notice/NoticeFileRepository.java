package com.git.repository.board.notice;

import com.git.domain.board.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
