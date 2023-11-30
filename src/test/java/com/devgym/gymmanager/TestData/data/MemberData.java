package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.auth.dto.SignUpRequest;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;

public class MemberData {
    public static SignUpRequest getMemberRequest(){
        return new SignUpRequest("injun", "tomato2480*", "01034622480", Membership.HALF_YEAR);
    }
    public static SignUpRequest getMemberRequest2(){
        return new SignUpRequest("hyun", "tomato2480*", "01088554433", Membership.HALF_YEAR);
    }
    public static Member getMember(){
        return Member.createMember(getMemberRequest());
    }
    public static Member getMember2(){
        return Member.createMember(getMemberRequest2());
    }
}
