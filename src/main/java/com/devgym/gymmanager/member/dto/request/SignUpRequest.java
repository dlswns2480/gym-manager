package com.devgym.gymmanager.member.dto.request;

import com.devgym.gymmanager.member.domain.Membership;

public record SignUpRequest(String memberName, String passWord, String phoneNumber,
                            Membership membership) {

}
