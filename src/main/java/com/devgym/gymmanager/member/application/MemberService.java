package com.devgym.gymmanager.member.application;

import static com.devgym.gymmanager.common.exception.ErrorCode.*;
import static com.devgym.gymmanager.common.exception.ErrorCode.INVALID_PASSWORD;
import static com.devgym.gymmanager.common.exception.ErrorCode.NOT_EXIST_MEMBER;

import com.devgym.gymmanager.common.exception.CustomException;
import com.devgym.gymmanager.common.exception.ErrorCode;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.domain.Membership;
import com.devgym.gymmanager.member.dto.request.LoginRequest;
import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.member.dto.response.MemberResponse;
import com.devgym.gymmanager.member.dto.response.TokenResponse;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.member.repository.MemberRepository;
import com.devgym.gymmanager.trainer.application.TrainerService;
import com.devgym.gymmanager.trainer.domain.Trainer;
import com.devgym.gymmanager.trainer.dto.request.AddTrainer;
import com.devgym.gymmanager.trainer.dto.response.TrainerResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.access-secret}")
    private String secretKey;
    @Value("${jwt.refresh-secret}")
    private String refreshSecretKey;

    private final MemberRepository memberRepository;
    private final TrainerService trainerService;

    @Transactional
    public MemberResponse signUp(SignUpRequest request) {
        validateDuplicate(request); // 중복검증
        String encodedPassWd = encoder.encode(request.passWord());
        SignUpRequest signUpRequest = new SignUpRequest(request.memberName(), encodedPassWd,
            request.phoneNumber(), request.membership());
        Member member = Member.createMember(signUpRequest); // 회원 생성
        Member savedMember = memberRepository.save(member); // 회원 저장
        return new MemberResponse(savedMember.getName(), savedMember.getMembership());
    }

    @Transactional
    public TokenResponse signIn(LoginRequest request) {
        String name = request.memberName();
        Member member = memberRepository.findByName(name)
            .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));
        if (!encoder.matches(request.passWord(), member.getPassWord())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        Long accessExpiredMs = 1000 * 60 * 60L;
        Long refreshExpiredMs = 30 * 24 * 1000 * 60 * 60L;
        String accessToken = JwtUtil.createAccessToken(request.memberName(), secretKey,
            accessExpiredMs);
        String refreshToken = JwtUtil.createRefreshToekn(request.memberName(), refreshSecretKey,
            accessExpiredMs);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public TrainerResponse registerTrainer(AddTrainer request) {
        Trainer trainer = trainerService.findByIdService(request.trainerId()); // 트레이너가 있는 지 확인
        Member member = memberRepository.findById(request.memberId())
            .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));
        member.setTrainer(trainer);
        return new TrainerResponse(trainer.getName(), trainer.getPhoneNumber(), trainer.getCareer(),
            trainer.getHourlyPrice());
    }

    public List<MemberResponse> findByMembership(Membership membership) {
        List<Member> all = memberRepository.findByMembership(membership);
        return all.stream()
            .map(member -> new MemberResponse(member.getName(), member.getMembership()))
            .toList();
    }

    public List<MemberResponse> findAllMembers() {
        List<Member> all = memberRepository.findAll();
        return all.stream()
            .map(member -> new MemberResponse(member.getName(), member.getMembership()))
            .toList();
    }

    private void validateDuplicate(SignUpRequest request) {
        Optional<Member> member = memberRepository.findByPhoneNumber(request.phoneNumber());
        if (member.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member findByIdService(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));
    }


}
