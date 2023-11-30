package com.devgym.gymmanager.auth.dto;

import com.devgym.gymmanager.domain.type.Membership;

public record SignUpRequest(String memberName, String passWord, String phoneNumber, Membership membership) {
}
