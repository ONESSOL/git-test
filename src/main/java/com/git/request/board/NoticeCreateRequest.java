package com.git.request.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class NoticeCreateRequest {

    private String title;
    private String contents;
    private List<MultipartFile> boardFiles;
}
