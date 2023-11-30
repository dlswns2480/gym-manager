package com.devgym.gymmanager.common.exception;

public class NotFoundInfoException extends RuntimeException{
    public NotFoundInfoException() {
        super("존재하지 않는 정보입니다");

    }
}
