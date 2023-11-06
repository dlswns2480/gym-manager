package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.MemberRequest;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.exception.NotFoundInfoException;
import com.devgym.gymmanager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createMember(MemberRequest request){
        validateDuplicate(request); // 중복검증
        Member member = Member.createMember(request);
        Member result = memberRepository.save(member);
        return new MemberResponse(result.getName(), result.getMembership());
    }
    public MemberResponse findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundInfoException::new);
        return new MemberResponse(member.getName(), member.getMembership());
    }
    public MemberResponse findByMembership(Membership membership){
        Member member = memberRepository.findByMembership(membership).orElseThrow(NotFoundInfoException::new);
        return new MemberResponse(member.getName(), member.getMembership());
    }
    public List<MemberResponse> findAllMembers(){
        List<Member> all = memberRepository.findAll();
        return all.stream()
                .map(member -> new MemberResponse(member.getName(), member.getMembership()))
                .toList();
    }
    private void validateDuplicate(MemberRequest request){
        Optional<Member> member = memberRepository.findByPhoneNumber(request.phoneNumber());
        if(member.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    protected Member findByIdService(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(NotFoundInfoException::new);
    }


}
