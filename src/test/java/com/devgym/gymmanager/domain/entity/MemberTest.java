package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    @DisplayName("멤버 생성에 성공한다")
    void createMember() {
        //given
        String name = "injun";
        String phoneNumber = "01034622480";
        Membership membership = Membership.HALF_YEAR;
        MemberRequest memberRequest = new MemberRequest(name, phoneNumber, membership);
        //when
        Member member = Member.createMember(memberRequest);
        //then
        Assertions.assertThat(member.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("010으로 시작하는 것이 아닌 번호는 예외가 발생한다.")
    void createMemberWithWrongNumber(){
        //given
        String name = "injun";
        String phoneNumber = "0034622480";
        Membership membership = Membership.HALF_YEAR;
        MemberRequest memberRequest = new MemberRequest(name, phoneNumber, membership);
        //when then
        assertThrows(IllegalStateException.class, () -> Member.createMember(memberRequest));
    }
}