package com.git.oauth.handler;

import com.git.domain.member.Member;
import com.git.exception.member.MemberNotFoundException;
import com.git.jwt.AuthTokenGenerator;
import com.git.jwt.JwtTokenProvider;
import com.git.oauth.CustomOAuth2User;
import com.git.repository.member.MemberRepository;
import com.git.service.redis.RedisService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import static com.git.domain.member.Role.GUEST;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            if (oAuth2User.getRole().equals(GUEST)) {
                long now = new Date().getTime();
                Member findMember = memberRepository.findBySocialId(authentication.getName()).orElseThrow(MemberNotFoundException::new);
                String subject = String.valueOf(findMember.getId());
                String accessToken = jwtTokenProvider.generate(subject, new Date(now + AuthTokenGenerator.ACCESS_TOKEN_EXPIRE_TIME), authentication);
                String refreshToken = jwtTokenProvider.generate(subject, new Date(now + AuthTokenGenerator.REFRESH_TOKEN_EXPIRE_TIME), authentication);

                redisService.setValues(subject, refreshToken, Duration.ofMillis(AuthTokenGenerator.REFRESH_TOKEN_EXPIRE_TIME));
                jwtTokenProvider.accessTokenSetHeaders(accessToken, response);
                response.sendRedirect("/");
            } else {
                loginSuccess(response, authentication);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, Authentication authentication) {
        long now = new Date().getTime();
        String subject = authentication.getName();
        String accessToken = jwtTokenProvider.generate(subject, new Date(now + AuthTokenGenerator.ACCESS_TOKEN_EXPIRE_TIME), authentication);
        String refreshToken = jwtTokenProvider.generate(subject, new Date(now + AuthTokenGenerator.REFRESH_TOKEN_EXPIRE_TIME), authentication);
        redisService.setValues(subject, refreshToken, Duration.ofMillis(AuthTokenGenerator.REFRESH_TOKEN_EXPIRE_TIME));
        jwtTokenProvider.accessTokenSetHeaders(accessToken, response);
    }
}
























