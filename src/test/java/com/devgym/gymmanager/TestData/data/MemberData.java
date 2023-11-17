package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.MemberRequest;

public class MemberData {
    public static MemberRequest getMemberRequest(){
        return new MemberRequest("injun", "01034622480", Membership.HALF_YEAR);
    }
    public static MemberRequest getMemberRequest2(){
        return new MemberRequest("hyun", "01088554433", Membership.HALF_YEAR);
    }
    public static Member getMember(){
        return Member.createMember(getMemberRequest());
    }
    public static Member getMember2(){
        return Member.createMember(getMemberRequest2());
    }
}
