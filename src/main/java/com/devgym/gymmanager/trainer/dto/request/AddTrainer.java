package com.devgym.gymmanager.trainer.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddTrainer(
    @NotBlank(message = "회원 입력은 필수입니다.")
    Long memberId,
    @NotBlank(message = "트레이너 입력은 필수입니다.")
    Long trainerId) {

}
