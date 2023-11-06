package com.devgym.gymmanager.dto.response;

import com.devgym.gymmanager.domain.type.Membership;

public record MemberResponse(String name, Membership membership) {
}
