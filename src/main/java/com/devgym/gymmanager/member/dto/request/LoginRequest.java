package com.devgym.gymmanager.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        String memberName,
        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String passWord) {
}
