package com.devgym.gymmanager.review.dto.request;

public record ReviewRequest(Long memberId, int score, String content) {

}
