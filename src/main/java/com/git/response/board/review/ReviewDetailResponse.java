package com.git.response.board.review;

import com.git.domain.board.review.Review;
import com.git.domain.board.review.ReviewFileEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ReviewDetailResponse {

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
    private List<MultipartFile> boardFiles;

    public static ReviewDetailResponse toSave(Review review) {
        ReviewDetailResponse response = new ReviewDetailResponse();
        response.setId(review.getId());
        response.setTitle(review.getTitle());
        response.setContents(review.getContents());
        response.setCreatedBy(review.getCreatedBy());
        response.setUpdatedBy(review.getUpdatedBy());
        response.setCreatedTime(review.getCreatedTime());
        response.setUpdatedTime(review.getUpdatedTime());
        if(review.getFileAttached() == 1) {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for (ReviewFileEntity reviewFileEntity : review.getBoardFileEntities()) {
                originalFileNameList.add(reviewFileEntity.getOriginalFileName());
                storedFileNameList.add(reviewFileEntity.getStoredFileName());
            }
            response.setOriginalFileNames(originalFileNameList);
            response.setStoredFileNames(storedFileNameList);
        }
        return response;
    }
}













