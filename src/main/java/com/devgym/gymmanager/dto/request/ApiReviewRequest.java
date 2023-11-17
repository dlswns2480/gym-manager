package com.devgym.gymmanager.dto.request;

import com.devgym.gymmanager.domain.entity.Member;

public record ApiReviewRequest(Member member, int score, String content) {
}
