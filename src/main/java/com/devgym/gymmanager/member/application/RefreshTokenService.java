package com.devgym.gymmanager.member.application;

import com.devgym.gymmanager.common.exception.CustomException;
import com.devgym.gymmanager.common.exception.ErrorCode;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.dto.response.TokenResponse;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final MemberRepository memberRepository;
    @Value("${jwt.access-secret}")
    private String secretKey;
    @Value("${jwt.refresh-secret}")
    private String refreshSecretKey;

    public TokenResponse reGetToken(String memberName) {
        log.info("member's name : {}", memberName);
        Member member = memberRepository.findByName(memberName)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        Long accessExpiredMs = 1000 * 60 * 60L;
        Long refreshExpiredMs = 30 * 24 * 1000 * 60 * 60L;
        String accessToken = JwtUtil.createAccessToken(memberName, secretKey, accessExpiredMs);
        String refreshToken = JwtUtil.createRefreshToekn(memberName, refreshSecretKey,
            refreshExpiredMs);
        return new TokenResponse(accessToken, refreshToken);
    }
}
