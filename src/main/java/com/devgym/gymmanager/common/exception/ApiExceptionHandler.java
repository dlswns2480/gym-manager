package com.devgym.gymmanager.common.exception;

import com.devgym.gymmanager.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getMessage());
        log.error("{}",e.getBindingResult().getFieldErrors());
        StringBuilder sb = new StringBuilder();
        for (FieldError ex : e.getBindingResult().getFieldErrors()) {
            sb.append(ex.getCode());
            sb.append(", ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(sb.toString()));
    }
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> errorHandler(CommonException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }
}
