package com.devgym.gymmanager.dto.request;

import com.devgym.gymmanager.domain.entity.Member;

public record ReviewRequest(Long memberId, int score, String content) {
}
