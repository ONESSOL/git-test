package com.git.controller.auth;

import com.git.config.SecurityUtil;
import com.git.jwt.AuthTokens;
import com.git.jwt.JwtAuthenticationEntryPoint;
import com.git.jwt.JwtTokenProvider;
import com.git.request.member.LoginRequest;
import com.git.request.member.MemberCreateRequest;
import com.git.response.member.MemberCreateResponse;
import com.git.service.auth.AuthService;
import com.git.service.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/save")
    public ResponseEntity<MemberCreateResponse> saveMember(@RequestBody MemberCreateRequest request) {
        return ResponseEntity.ok(authService.saveMember(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokens> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PatchMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.resolveToken(request);
        String refreshToken = redisService.getValues(String.valueOf(SecurityUtil.currentMemberId()));
        authService.logout(accessToken, refreshToken);
        return ResponseEntity.ok().build();
    }
}


































