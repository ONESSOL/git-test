package com.git.service.board;

import com.git.domain.board.BoardFileEntity;
import com.git.domain.board.Notice;
import com.git.exception.board.BoardNotFoundException;
import com.git.repository.board.notice.NoticeFileRepository;
import com.git.repository.board.notice.NoticeRepository;
import com.git.request.board.NoticeCreateRequest;
import com.git.request.board.NoticeUpdateRequest;
import com.git.response.board.NoticeCreateResponse;
import com.git.response.board.NoticeDetailResponse;
import com.git.response.board.NoticeListResponse;
import com.git.response.board.NoticeUpdateResponse;
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
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;

    @Transactional
    public NoticeCreateResponse saveNotice(NoticeCreateRequest request) throws IOException {

        if (request.getBoardFiles().get(0).isEmpty()) {
            Notice notice = noticeRepository.save(Notice.builder()
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .fileAttached(0)
                    .build());
            return NoticeCreateResponse.toSave(notice);
        } else {
            Notice notice = noticeRepository.save(Notice.builder()
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .fileAttached(1)
                    .build());
            String originalFileName = "";
            String storedFileName = "";
            for (MultipartFile boardFile : request.getBoardFiles()) {
                originalFileName = boardFile.getOriginalFilename();
                storedFileName = UUID.randomUUID() + "_" + originalFileName;
                String savePath = "C:\\Users\\user\\Desktop\\board\\notice\\image\\" + storedFileName;
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                boardFile.transferTo(file);
                noticeFileRepository.save(
                        BoardFileEntity.builder()
                                .originalFileName(originalFileName)
                                .storedFileName(storedFileName)
                                .notice(notice)
                                .build());
            }
            return NoticeCreateResponse.toSave(notice);
        }
    }

    @Transactional(readOnly = true)
    public NoticeDetailResponse findById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(BoardNotFoundException::new);
        return NoticeDetailResponse.toSave(notice);
    }

    @Transactional(readOnly = true)
    public Page<NoticeListResponse> findAll(Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Notice> noticeList = noticeRepository.findAll(pageRequest);
        return noticeList.map(NoticeListResponse::toSave);
    }

    @Transactional(readOnly = true)
    public Page<NoticeListResponse> findByTitleAndContents(String search, Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Notice> noticeList = noticeRepository.findByTitleAndContents(search, pageRequest);
        return noticeList.map(NoticeListResponse::toSave);
    }

    @Transactional
    public NoticeUpdateResponse update(Long noticeId, NoticeUpdateRequest request) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(BoardNotFoundException::new);
        if (notice.getFileAttached() == 0) {
            if (request.getBoardFiles().get(0).isEmpty()) {
                request.setFileAttached(0);
                notice.update(request.getTitle(), request.getContents(), request.getFileAttached());
            } else {
                request.setFileAttached(1);
                notice.update(request.getTitle(), request.getContents(), request.getFileAttached());
                for (MultipartFile boardFile : request.getBoardFiles()) {
                    String originalFileName = boardFile.getOriginalFilename();
                    String storedFileName = UUID.randomUUID() + "_" + boardFile.getOriginalFilename();
                    String savePath = "C:\\Users\\user\\Desktop\\board\\notice\\image\\" + storedFileName;
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    boardFile.transferTo(file);
                    noticeFileRepository.save(BoardFileEntity.builder()
                            .originalFileName(originalFileName)
                            .storedFileName(storedFileName)
                            .notice(notice)
                            .build());
                }
            }
        } else {
            if (request.getBoardFiles().get(0).isEmpty()) {
                request.setFileAttached(0);
                for (BoardFileEntity boardFileEntity : notice.getBoardFileEntities()) {
                    deleteFile(boardFileEntity);
                }
                notice.getBoardFileEntities().removeAll(notice.getBoardFileEntities());
                notice.update(request.getTitle(), request.getContents(), request.getFileAttached());
            } else {
                request.setFileAttached(1);
                for (BoardFileEntity boardFileEntity : notice.getBoardFileEntities()) {
                    deleteFile(boardFileEntity);
                }
                notice.getBoardFileEntities().removeAll(notice.getBoardFileEntities());
                for (MultipartFile boardFile : request.getBoardFiles()) {
                    String originalFileName = boardFile.getOriginalFilename();
                    String storedFileName = UUID.randomUUID() + "_" + originalFileName;
                    String savePath = "C:\\Users\\user\\Desktop\\board\\notice\\image\\" + storedFileName;
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    boardFile.transferTo(file);
                    noticeFileRepository.save(BoardFileEntity.builder()
                            .originalFileName(originalFileName)
                            .storedFileName(storedFileName)
                            .notice(notice)
                            .build());
                }
                notice.update(request.getTitle(), request.getContents(), request.getFileAttached());
            }
        }
        return NoticeUpdateResponse.toSave(notice);
    }

    @Transactional
    public void delete(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(BoardNotFoundException::new);
        if(notice.getFileAttached() == 1) {
            for (BoardFileEntity boardFileEntity : notice.getBoardFileEntities()) {
                deleteFile(boardFileEntity);
            }
        }
        noticeRepository.deleteById(noticeId);
    }

    private void deleteFile(BoardFileEntity boardFileEntity) {
        noticeFileRepository.delete(boardFileEntity);
    }
}





















