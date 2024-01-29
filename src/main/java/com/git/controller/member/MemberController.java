package com.git.controller.member;

import com.git.config.SecurityUtil;
import com.git.request.member.MemberUpdateRequest;
import com.git.request.member.PasswordChangeRequest;
import com.git.response.member.MemberDetailResponse;
import com.git.response.member.MemberUpdateResponse;
import com.git.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/my_info")
    public ResponseEntity<MemberDetailResponse> myInfo() {
        return ResponseEntity.ok(memberService.myInfo(SecurityUtil.currentMemberId()));
    }

    @PutMapping("/change/password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest request) {
        memberService.changePassword(SecurityUtil.currentMemberId(), request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<MemberUpdateResponse> update(@RequestBody MemberUpdateRequest request) {
        return ResponseEntity.ok(memberService.update(SecurityUtil.currentMemberId(), request));
    }
}










































