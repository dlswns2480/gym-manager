package com.devgym.gymmanager.common.validator;

import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

@Component
@Slf4j
public class SignUpValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("Validator에 들어옴");
        ValidationUtils.rejectIfEmpty(errors, "memberName", "아이디는 필수입니다.");
        ValidationUtils.rejectIfEmpty(errors, "passWord", "비밀번호는 필수입니다.");
        ValidationUtils.rejectIfEmpty(errors, "membership", "멤버십 기입은 필수입니다.");
        SignUpRequest request = (SignUpRequest) target;
        Pattern emailPattern = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);
        Pattern phonePattern = compile("^\\d{2,3}-\\d{3,4}-\\d{4}$", CASE_INSENSITIVE);
        if (!(emailPattern.matcher(request.memberName()).matches())) {
            errors.rejectValue("memberName", "ERROR", "이메일 형식이 올바르지 않습니다.");
        }
        if (!(phonePattern.matcher(request.phoneNumber()).matches())) {
            log.info(request.phoneNumber());
            errors.rejectValue("phoneNumber", "ERROR","전화번호 형식이 올바르지 않습니다.");
        }
    }
}
