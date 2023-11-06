package com.devgym.gymmanager.dto;

import com.devgym.gymmanager.domain.entity.Member;

public record ReviewRequest(Member member, int score, String content) {
}
