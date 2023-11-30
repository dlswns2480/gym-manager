package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.TestData.data.TrainerData;
import com.devgym.gymmanager.trainer.domain.Trainer;
import com.devgym.gymmanager.trainer.dto.request.TrainerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {
    @Test
    @DisplayName("트레이너를 생성할 수 있다")
    void createTrainer() {
        TrainerRequest requeest = TrainerData.getTrainerRequest();
        Trainer trainer = Trainer.createTrainer(requeest);

        assertAll(
                () -> assertThat(trainer.getName()).isEqualTo(requeest.name()),
                () -> assertThat(trainer.getCareer()).isEqualTo(requeest.career()),
                () -> assertThat(trainer.getHourlyPrice()).isEqualTo(requeest.hourlyPrice()),
                () -> assertThat(trainer.getPhoneNumber()).isEqualTo(requeest.phoneNumber())
        );
    }

    @Test
    @DisplayName("폰 번호가 010으로 시작하지 않으면 예외가 발생한다")
    void createWithWrongPhoneNumber(){
        TrainerRequest request = new TrainerRequest("jun", "10134622480", 5, 10000);
        assertThrows(IllegalStateException.class, () -> Trainer.createTrainer(request));
    }
}