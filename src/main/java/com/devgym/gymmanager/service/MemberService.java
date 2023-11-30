package com.devgym.gymmanager.service;

import com.devgym.gymmanager.auth.dto.LoginRequest;
import com.devgym.gymmanager.auth.dto.SignUpRequest;
import com.devgym.gymmanager.auth.utils.JwtUtil;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.AddTrainer;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.exception.CustomException;
import com.devgym.gymmanager.exception.ErrorCode;
import com.devgym.gymmanager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.devgym.gymmanager.exception.ErrorCode.INVALID_PASSWORD;
import static com.devgym.gymmanager.exception.ErrorCode.NOT_EXIST_MEMBER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000* 60 * 60L;
    private final MemberRepository memberRepository;
    private final TrainerService trainerService;
    @Transactional
    public MemberResponse signUp(SignUpRequest request){
        validateDuplicate(request); // 중복검증
        String encodedPassWd = encoder.encode(request.passWord());
        SignUpRequest signUpRequest = new SignUpRequest(request.memberName(), encodedPassWd, request.phoneNumber(), request.membership());
        Member member = Member.createMember(signUpRequest); // 회원 생성
        Member savedMember = memberRepository.save(member); // 회원 저장
        return new MemberResponse(savedMember.getName(), savedMember.getMembership());
    }
    @Transactional
    public String signIn(LoginRequest request) {
        String name = request.memberName();
        Member member = memberRepository.findByName(name).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        if(!member.getPassWord().equals(request.passWord())){
            throw new CustomException(INVALID_PASSWORD);
        }

        return JwtUtil.createJwt(request.memberName(), secretKey, expiredMs);
    }
    @Transactional
    public TrainerResponse registerTrainer(AddTrainer request) {
        Trainer trainer = trainerService.findByIdService(request.trainerId()); // 트레이너가 있는 지 확인
        Member member = memberRepository.findById(request.memberId()).orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));
        member.setTrainer(trainer);
        return new TrainerResponse(trainer.getName(), trainer.getPhoneNumber(), trainer.getCareer(), trainer.getHourlyPrice());
    }
    public List<MemberResponse> findByMembership(Membership membership){
        List<Member> all = memberRepository.findByMembership(membership);
        return all.stream()
                .map(member -> new MemberResponse(member.getName(), member.getMembership()))
                .toList();
    }
    public List<MemberResponse> findAllMembers(){
        List<Member> all = memberRepository.findAll();
        return all.stream()
                .map(member -> new MemberResponse(member.getName(), member.getMembership()))
                .toList();
    }
    private void validateDuplicate(SignUpRequest request){
        Optional<Member> member = memberRepository.findByPhoneNumber(request.phoneNumber());
        if(member.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member findByIdService(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));
    }


}
