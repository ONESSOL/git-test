package com.git.service.board;

import com.git.domain.board.review.Review;
import com.git.domain.board.review.ReviewFileEntity;
import com.git.domain.member.Member;
import com.git.exception.member.MemberNotFoundException;
import com.git.repository.board.review.ReviewFileRepository;
import com.git.repository.board.review.ReviewRepository;
import com.git.repository.member.MemberRepository;
import com.git.request.board.review.ReviewCreateRequest;
import com.git.response.board.review.ReviewCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public ReviewCreateResponse saveReview(Long memberId, ReviewCreateRequest request) throws IOException {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        if (request.getBoardFiles().get(0).isEmpty()) {
            Review review = reviewRepository.save(Review.builder()
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .fileAttached(0)
                    .member(member)
                    .build());
            return ReviewCreateResponse.toSave(review);
        } else {
            Review review = reviewRepository.save(Review.builder()
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .fileAttached(1)
                    .member(member)
                    .build());
            String originalFileName = "";
            String storedFileName = "";
            for (MultipartFile boardFile : request.getBoardFiles()) {
                originalFileName = boardFile.getOriginalFilename();
                storedFileName = UUID.randomUUID() + "_" + originalFileName;
                String savePath = "C:\\Users\\User\\Desktop\\board_image\\" + storedFileName;
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                boardFile.transferTo(file);
                ReviewFileEntity reviewFile = reviewFileRepository.save(
                        ReviewFileEntity.builder()
                                .originalFileName(originalFileName)
                                .storedFileName(storedFileName)
                                .review(review)
                                .build());
            }
            return ReviewCreateResponse.toSave(review);
        }
    }
}
