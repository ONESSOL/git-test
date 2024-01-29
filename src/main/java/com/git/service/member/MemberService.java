package com.git.service.member;

import com.git.domain.member.Member;
import com.git.exception.member.CheckNewPasswordException;
import com.git.exception.member.MemberNotFoundException;
import com.git.exception.member.PasswordNotMatchException;
import com.git.repository.member.MemberRepository;
import com.git.request.member.MemberUpdateRequest;
import com.git.request.member.PasswordChangeRequest;
import com.git.response.member.MemberDetailResponse;
import com.git.response.member.MemberUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberDetailResponse myInfo(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        return MemberDetailResponse.toSave(member);
    }

    @Transactional
    public void changePassword(Long memberId, PasswordChangeRequest request) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        if(!passwordEncoder.matches(request.getBeforePassword(), member.getPassword())) {
            throw new PasswordNotMatchException();
        }
        if(!request.getAfterPassword().equals(request.getCheckAfterPassword())) {
            throw new CheckNewPasswordException();
        }
        String encodePassword = passwordEncoder.encode(request.getAfterPassword());
        member.changePassword(encodePassword);
    }

    @Transactional
    public MemberUpdateResponse update(Long memberId, MemberUpdateRequest request) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        member.update(request.getPhoneNum(), request.getEmail(), request.getAddress());
        return MemberUpdateResponse.toSave(member);
    }
}
















































