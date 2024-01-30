package com.git.response.board.notice;

import com.git.domain.board.notice.Notice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class NoticeListResponse {

    private Long id;
    private String title;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static NoticeListResponse toSave(Notice notice) {
        NoticeListResponse response = new NoticeListResponse();
        response.setId(notice.getId());
        response.setTitle(notice.getTitle());
        response.setCreatedBy(notice.getCreatedBy());
        response.setUpdatedBy(notice.getUpdatedBy());
        response.setCreatedTime(notice.getCreatedTime());
        response.setUpdatedTime(notice.getUpdatedTime());
        return response;
    }
}
