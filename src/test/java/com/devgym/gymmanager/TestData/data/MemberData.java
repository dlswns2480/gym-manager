package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.member.dto.request.LoginRequest;
import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.domain.Membership;

public class MemberData {
    public static SignUpRequest getMemberRequest(){
        return new SignUpRequest("injun@naver.com", "tomato2480*", "010-3462-2480", Membership.HALF_YEAR);
    }
    public static SignUpRequest getMemberRequest2(){
        return new SignUpRequest("hyun", "tomato2480*", "01088554433", Membership.HALF_YEAR);
    }
    public static LoginRequest getLoginRequest(){
        return new LoginRequest("injun@naver.com", "tomato2480*");
    }
    public static Member getMember(){
        return Member.createMember(getMemberRequest());
    }
    public static Member getMember2(){
        return Member.createMember(getMemberRequest2());
    }
}
