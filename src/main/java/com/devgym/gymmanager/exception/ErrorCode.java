package com.devgym.gymmanager.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_EXIST_MEMBER("존재하지 않는 회원입니다"),
    NOT_EXIST_TRAINER("존재하지 않는 트레이너 입니다"),
    NOT_EXIST_ITEM("존재하지 않는 아이템입니다");
    private final String message;
}
