package com.git.request.board.notice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class NoticeUpdateRequest {

    private String title;
    private String contents;
    private int fileAttached;
    private List<MultipartFile> boardFiles;

}
