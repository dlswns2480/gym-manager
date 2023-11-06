package com.devgym.gymmanager.TestData.member;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.MemberRequest;
import com.devgym.gymmanager.service.MemberService;

public class MemberData {
    public static MemberRequest getMemberRequest(){
        return new MemberRequest("injun", "01034622480", Membership.HALF_YEAR);
    }
    public static Member getMember(){
        return Member.createMember(getMemberRequest());
    }
}
