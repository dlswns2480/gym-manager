package com.devgym.gymmanager.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomException extends CommonException{
    private final ErrorCode errorCode;
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}