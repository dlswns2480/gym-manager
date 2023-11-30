package com.devgym.gymmanager.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomException extends CommonException{
    private final ErrorCode errorCode;
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
