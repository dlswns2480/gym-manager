package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.dto.MemberRequest;
import com.devgym.gymmanager.dto.MemberResponse;
import com.devgym.gymmanager.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberResponse createMember(MemberRequest request){
        Member member = Member.createMember(request);
        Member result = memberRepository.save(member);
        return new MemberResponse(result.getName(), result.getMembership());
    }
}
