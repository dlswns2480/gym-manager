package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.member.domain.Membership;
import com.devgym.gymmanager.member.domain.Member;
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
        String passWord = "tomato2480*";
        Membership membership = Membership.HALF_YEAR;
        SignUpRequest memberRequest = new SignUpRequest(name, passWord, phoneNumber, membership);
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
        String passWord = "tomato2480*";
        String phoneNumber = "0034622480";
        Membership membership = Membership.HALF_YEAR;
        SignUpRequest memberRequest = new SignUpRequest(name, passWord, phoneNumber, membership);
        //when then
        assertThrows(IllegalStateException.class, () -> Member.createMember(memberRequest));
    }
}