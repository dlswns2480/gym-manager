package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.member.TrainerData;
import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.repository.TrainerRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Mock
    TrainerRepository trainerRepository;
    @InjectMocks
    TrainerService trainerService;

    @Test
    @DisplayName("트레이너 생성에 성공한다")
    void create() {
        TrainerRequest trainerRequest = TrainerData.getTrainerRequest();
        Trainer trainer = TrainerData.getTrainer();
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

        TrainerResponse result = trainerService.createTrainer(trainerRequest);

        assertAll(
                () -> assertThat(trainer.getName()).isEqualTo(result.name()),
                () -> assertThat(trainer.getCareer()).isEqualTo(result.career()),
                () -> assertThat(trainer.getHourlyPrice()).isEqualTo(result.hourlyPrice())
        );
    }

    @Test
    @DisplayName("이미 존재하는 트레이너 생성 시 예외가 발생한다")
    void createAlreadyExist() {
        Trainer trainer = TrainerData.getTrainer();
        when(trainerRepository.findByPhoneNumber(any(String.class))).thenReturn(Optional.ofNullable(trainer));

        assertThrows(DuplicateRequestException.class, () -> trainerService.createTrainer(TrainerData.getTrainerRequest()));
    }

    @Test
    @DisplayName("특정 경력 이상의 트레이너들을 조회할 수 있다")
    void findByCareer() {
        List<Trainer> all = new ArrayList<>();
        all.add(TrainerData.getTrainer());
        when(trainerRepository.findAllByCareerGreaterThanEqual(any(Integer.class))).thenReturn(all);

        List<TrainerResponse> result = trainerService.findByCareerGreaterThan(3);

        assertAll(
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).career()).isEqualTo(all.get(0).getCareer()),
                () -> assertThat(result.get(0).hourlyPrice()).isEqualTo(all.get(0).getHourlyPrice()
                ));
    }

    @Test
    @DisplayName("0보다 작은 경력의 트레이너를 조회 시 예외가 발생한다")
    void findByCareerWithZero(){
        assertThrows(IllegalArgumentException.class, () -> trainerService.findByCareerGreaterThan(-1));
    }
}