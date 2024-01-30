package com.git.domain.board.notice;

import com.git.domain.board.review.Review;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class NoticeFileEntity {

    @Id @GeneratedValue
    @Column(name = "board_file_id")
    private Long id;
    private String originalFileName;
    private String storedFileName;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;


    @Builder
    public NoticeFileEntity(String originalFileName, String storedFileName, Notice notice) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        createNotice(notice);
    }

    protected NoticeFileEntity() {
    }

    public void createNotice(Notice notice) {
        this.notice = notice;
        notice.addBoardFile(this);
    }
}



















