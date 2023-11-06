package com.devgym.gymmanager.dto;

import com.devgym.gymmanager.domain.type.Membership;

public record MemberRequest(String name, String phoneNumber, Membership membership) {
}
