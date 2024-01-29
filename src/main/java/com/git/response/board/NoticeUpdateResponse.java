package com.git.response.board;

import com.git.domain.board.BoardFileEntity;
import com.git.domain.board.Notice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NoticeUpdateResponse {

    private Long id;
    private String title;
    private String contents;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private int fileAttached;
    private List<String> originalFileNames;
    private List<String> storedFileNames;

    public static NoticeUpdateResponse toSave(Notice notice) {
        NoticeUpdateResponse response = new NoticeUpdateResponse();
        response.setId(notice.getId());
        response.setTitle(notice.getTitle());
        response.setContents(notice.getContents());
        response.setCreatedBy(notice.getCreatedBy());
        response.setUpdatedBy(notice.getUpdatedBy());
        response.setCreatedTime(notice.getCreatedTime());
        response.setUpdatedTime(notice.getUpdatedTime());
        if(notice.getFileAttached() == 0) {
            response.setFileAttached(0);
        } else {
            response.setFileAttached(1);
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for(BoardFileEntity boardFileEntity : notice.getBoardFileEntities()) {
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }
            response.setOriginalFileNames(originalFileNameList);
            response.setStoredFileNames(storedFileNameList);
        }
        return response;
    }
}

























