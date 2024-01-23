package com.devgym.gymmanager.review.dto.request;

import com.devgym.gymmanager.member.domain.Member;

public record CreateReview(Member member, int score, String content) {

}
