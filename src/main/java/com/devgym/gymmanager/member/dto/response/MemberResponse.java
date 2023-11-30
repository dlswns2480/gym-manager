package com.devgym.gymmanager.member.dto.response;

import com.devgym.gymmanager.member.domain.Membership;

public record MemberResponse(String name, Membership membership) {
}
