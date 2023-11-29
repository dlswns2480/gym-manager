package com.devgym.gymmanager.service;

import com.devgym.gymmanager.auth.dto.SignUpRequest;
import com.devgym.gymmanager.auth.utils.JwtUtil;
import com.devgym.gymmanager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    @Value("${jwt.secret}")
    private String secretKey;
    private final MemberRepository memberRepository;

    private Long expiredMs = 1000* 60 * 60L;
    public String signUp(SignUpRequest request){
//        String name = request.userName();
//        Member member = memberRepository.findByName(name).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        //인증 부분
        return JwtUtil.createJwt(request.memberName(), secretKey, expiredMs);
    }
}
