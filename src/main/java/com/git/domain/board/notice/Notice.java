package com.git.domain.board.notice;

import com.git.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Notice extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;
    private String title;
    private String contents;
    private int fileAttached;
    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<NoticeFileEntity> boardFileEntities = new ArrayList<>();

    @Builder
    public Notice(String title, String contents, int fileAttached) {
        this.title = title;
        this.contents = contents;
        this.fileAttached = fileAttached;
    }

    protected Notice() {
    }

    public void addBoardFile(NoticeFileEntity noticeFileEntity) {
        this.boardFileEntities.add(noticeFileEntity);
    }

    public void update(String title, String contents, int fileAttached) {
        this.title = title;
        this.contents = contents;
        this.fileAttached = fileAttached;
    }
}






























