package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.AddTrainer;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.exception.NotFoundInfoException;
import com.devgym.gymmanager.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {
    @Mock
    MemberRepository memberRepository;
    @Mock
    TrainerService trainerService;
    @InjectMocks
    MemberService service;

    @Test
    @DisplayName("회원 생성에 성공한다")
    void save() {
        when(memberRepository.save(any(Member.class))).thenReturn(MemberData.getMember());
        MemberResponse member = service.createMember(MemberData.getMemberRequest());

        assertThat(member.name()).isEqualTo(MemberData.getMemberRequest().name());
    }

//    @Test
//    @DisplayName("멤버쉽으로 회원을 조회할 수 있다")
//    void findByMembership() {
//        //given
//        Member member = MemberData.getMember();
//
//        when(memberRepository.findByMembership(any(Membership.class))).thenReturn(Optional.of(member));
//
//        MemberResponse result = service.findByMembership(Membership.HALF_YEAR);
//        assertAll(
//                () -> assertThat(result.name()).isEqualTo(member.getName()),
//                () -> assertThat(result.membership()).isEqualTo(member.getMembership())
//        );
//    }

//    @Test
//    @DisplayName("해당 멤버쉽을 가진 고객이 없으면 예외가 발생한다")
//    void findByMemberShipNotExist() {
//        when(memberRepository.findByMembership(any(Membership.class))).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundInfoException.class, () -> service.findByMembership(Membership.HALF_YEAR));
//    }

    @Test
    @DisplayName("모든 회원 정보를 조회할 수 있다")
    void findAll() {
        List<Member> members = new ArrayList<>();
        members.add(MemberData.getMember());
        when(memberRepository.findAll()).thenReturn(members);

        List<MemberResponse> allMembers = service.findAllMembers();

        assertAll(
                () -> assertThat(allMembers.get(0).name()).isEqualTo(members.get(0).getName()),
                () -> assertThat(allMembers.get(0).membership()).isEqualTo(members.get(0).getMembership())
        );
    }

    @Test
    @DisplayName("회원에게 트레이너를 지정해줄 수 있다")
    void registerTrainer(){
        TrainerRequest teacher = new TrainerRequest("teacher", "01034622480", 4,20000);
        Trainer trainer = Trainer.createTrainer(teacher);
        Member member = MemberData.getMember();
        ReflectionTestUtils.setField(trainer, "id", 1L);
        ReflectionTestUtils.setField(member, "id", 1L);

        when(memberRepository.findById(any(Long.class))).thenReturn(Optional.of(member));
        when(trainerService.findByIdService(any(Long.class))).thenReturn(trainer);

        AddTrainer addTrainer = new AddTrainer(member.getId(), trainer.getId());
        TrainerResponse trainerResponse = service.registerTrainer(addTrainer);

        assertThat(trainer.getMemberList()).contains(member);
    }
}