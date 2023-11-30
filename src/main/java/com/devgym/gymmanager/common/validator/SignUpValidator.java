package com.devgym.gymmanager.common.validator;

import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@Slf4j
public class SignUpValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("Validator에[ 들어옴");
        ValidationUtils.rejectIfEmpty(errors, "memberName", "아이디는 필수입니다.");
        ValidationUtils.rejectIfEmpty(errors, "passWord", "비밀번호는 필수입니다.");
        ValidationUtils.rejectIfEmpty(errors, "membership", "멤버십 기입은 필수입니다.");
        SignUpRequest request = (SignUpRequest) target;
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Pattern phonePattern = Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$",
                Pattern.CASE_INSENSITIVE);
        if (!(emailPattern.matcher(request.memberName()).matches())) {
            errors.rejectValue("memberName", "이메일 형식이 올바르지 않습니다.");
        }
        if (!(phonePattern.matcher(request.memberName()).matches())) {
            errors.rejectValue("phoneNumber", "전화번호 형식이 올바르지 않습니다.");
        }
    }
}
