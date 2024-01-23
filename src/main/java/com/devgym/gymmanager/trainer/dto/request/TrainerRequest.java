package com.devgym.gymmanager.trainer.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TrainerRequest(
    @NotBlank(message = "이름 기입은 필수입니다.")
    String name,
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "전화번호 형식이 올바르지 않습니다.")
    String phoneNumber,
    @Min(value = 0, message = "경력은 1년 이상이어야 합니다.")
    int career,
    @Min(value = 0, message = "가격이 0원이하일 수 없습니다.")
    int hourlyPrice) {

}
