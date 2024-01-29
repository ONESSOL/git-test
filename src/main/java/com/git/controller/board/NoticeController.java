package com.git.controller.board;

import com.git.request.board.NoticeCreateRequest;
import com.git.request.board.NoticeUpdateRequest;
import com.git.response.board.NoticeCreateResponse;
import com.git.response.board.NoticeDetailResponse;
import com.git.response.board.NoticeListResponse;
import com.git.response.board.NoticeUpdateResponse;
import com.git.service.board.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/save")
    public ResponseEntity<NoticeCreateResponse> saveNotice(@ModelAttribute NoticeCreateRequest request) throws IOException {
        return ResponseEntity.ok(noticeService.saveNotice(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDetailResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<NoticeListResponse>> findAll(@PageableDefault(page = 1, sort = "id")Pageable pageable) {
        return ResponseEntity.ok(noticeService.findAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NoticeListResponse>> findByTitleAndContents(@RequestParam String search,
                                                                           @PageableDefault(page = 1, sort = "id")Pageable pageable) {
        return ResponseEntity.ok(noticeService.findByTitleAndContents(search, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeUpdateResponse> update(@PathVariable Long id, @ModelAttribute NoticeUpdateRequest request) throws IOException {
        return ResponseEntity.ok(noticeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noticeService.delete(id);
        return ResponseEntity.ok().build();
    }
}











































