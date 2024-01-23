package com.devgym.gymmanager.review.dto.request;

import com.devgym.gymmanager.member.domain.Member;

public record ApiReviewRequest(Member member, int score, String content) {

}
