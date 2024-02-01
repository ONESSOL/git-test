package com.git.service.board;

import com.git.domain.board.review.Review;
import com.git.domain.board.review.ReviewFileEntity;
import com.git.domain.member.Member;
import com.git.exception.board.BoardNotFoundException;
import com.git.exception.member.MemberNotFoundException;
import com.git.repository.board.review.ReviewFileRepository;
import com.git.repository.board.review.ReviewRepository;
import com.git.repository.member.MemberRepository;
import com.git.request.board.review.ReviewCreateRequest;
import com.git.request.board.review.ReviewUpdateRequest;
import com.git.response.board.review.ReviewCreateResponse;
import com.git.response.board.review.ReviewDetailResponse;
import com.git.response.board.review.ReviewListResponse;
import com.git.response.board.review.ReviewUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

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

    @Transactional(readOnly = true)
    public ReviewDetailResponse findById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(BoardNotFoundException::new);
        return ReviewDetailResponse.toSave(review);
    }

    @Transactional(readOnly = true)
    public Page<ReviewListResponse> findAll(Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Review> reviewList = reviewRepository.findAll(pageRequest);
        return reviewList.map(ReviewListResponse::toSave);
    }

    @Transactional(readOnly = true)
    public Page<ReviewListResponse> findByTitleAndContents(String search, Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Review> reviewList = reviewRepository.findByTitleAndContents(search, pageRequest);
        return reviewList.map(ReviewListResponse::toSave);
    }

    @Transactional
    public ReviewUpdateResponse update(Long reviewId, ReviewUpdateRequest request) throws IOException {

        Review review = reviewRepository.findById(reviewId).orElseThrow(BoardNotFoundException::new);
        if (review.getFileAttached() == 0) {
            if (request.getBoardFiles().get(0).isEmpty()) {
                review.update(request.getTitle(), request.getContents());
            } else {
                review.update(request.getTitle(), request.getContents());
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
                    reviewFileRepository.save(
                            ReviewFileEntity.builder()
                                    .originalFileName(originalFileName)
                                    .storedFileName(storedFileName)
                                    .review(review)
                                    .build());
                }
            }
        } else {
            if(request.getBoardFiles().get(0).isEmpty()) {
                for(ReviewFileEntity reviewFileEntity : review.getBoardFileEntities()) {
                    deleteFile(reviewFileEntity);
                }
                review.update(request.getTitle(), request.getContents());
            } else {
                for(ReviewFileEntity reviewFileEntity : review.getBoardFileEntities()) {
                    deleteFile(reviewFileEntity);
                }
                String originalFileName = "";
                String storedFileName = "";
                for(MultipartFile boardFile : request.getBoardFiles()) {
                    originalFileName = boardFile.getOriginalFilename();
                    storedFileName = UUID.randomUUID() + "_" + originalFileName;
                    String savePath = "C:\\Users\\User\\Desktop\\board_image\\" + storedFileName;
                    File file = new File(savePath);
                    if(!file.exists()) {
                        file.mkdirs();
                    }
                    boardFile.transferTo(file);
                    reviewFileRepository.save(
                            ReviewFileEntity.builder()
                                    .originalFileName(originalFileName)
                                    .storedFileName(storedFileName)
                                    .review(review)
                                    .build());
                }
                review.update(request.getTitle(), request.getContents());
            }
        }
        return ReviewUpdateResponse.toSave(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(BoardNotFoundException::new);
        for(ReviewFileEntity reviewFileEntity : review.getBoardFileEntities()) {
            deleteFile(reviewFileEntity);
        }
        reviewRepository.deleteById(reviewId);
    }

    private void deleteFile(ReviewFileEntity reviewFileEntity) {
        reviewFileRepository.delete(reviewFileEntity);
    }
}
























