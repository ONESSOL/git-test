package com.git.repository.member;

import com.git.domain.member.Member;
import com.git.domain.member.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findBySocialId(String socialId);

    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
