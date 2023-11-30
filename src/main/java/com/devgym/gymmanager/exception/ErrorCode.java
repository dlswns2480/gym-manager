package com.devgym.gymmanager.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_EXIST_MEMBER("존재하지 않는 회원입니다"),
    NOT_EXIST_TRAINER("존재하지 않는 트레이너 입니다"),
    NOT_EXIST_ITEM("존재하지 않는 아이템입니다"),
    EXPIRED_TOKEN("이미 만료된 토큰입니다."),
    NOT_EXIST_TOKEN("토큰이 비었습니다"),
    NOT_BEARER_TOKEN("Bearer 토큰이 아닙니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다");
    private final String message;
}
