package com.git.response.board.notice;

import com.git.domain.board.notice.NoticeFileEntity;
import com.git.domain.board.notice.Notice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeCreateResponse {

    private String title;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private int fileAttached;
    private List<MultipartFile> boardFiles = new ArrayList<>();
    private List<String> originalFileNames;
    private List<String> storedFileNames;

    public static NoticeCreateResponse toSave(Notice notice) {
        NoticeCreateResponse response = new NoticeCreateResponse();
        response.setTitle(notice.getTitle());
        response.setContents(notice.getContents());
        response.setCreatedTime(notice.getCreatedTime());
        response.setUpdatedTime(notice.getUpdatedTime());
        response.setCreatedBy(notice.getCreatedBy());
        response.setUpdatedBy(notice.getUpdatedBy());
        if(notice.getFileAttached() == 0) {
            response.setFileAttached(notice.getFileAttached());
        } else {
            response.setFileAttached(notice.getFileAttached());
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for(NoticeFileEntity noticeFileEntity : notice.getBoardFileEntities()) {
                originalFileNameList.add(noticeFileEntity.getOriginalFileName());
                storedFileNameList.add(noticeFileEntity.getStoredFileName());
            }
            response.setOriginalFileNames(originalFileNameList);
            response.setStoredFileNames(storedFileNameList);
        }
        return response;
    }
}








































