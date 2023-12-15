package com.devgym.gymmanager.repository;

import com.devgym.gymmanager.common.TestContainerSupport;
import com.devgym.gymmanager.trainer.domain.Trainer;
import com.devgym.gymmanager.trainer.dto.request.TrainerRequest;
import com.devgym.gymmanager.trainer.repository.TrainerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TrainerRepositoryTest{
    private final TrainerRepository trainerRepository;

    @Autowired
    TrainerRepositoryTest(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Test
    @DisplayName("특정 경력 이상의 트레이너들을 조회할 수 있다")
    void findByCareer(){
        TrainerRequest trainer1 = new TrainerRequest("jun", "01034444444", 5, 10000);
        TrainerRequest trainer2 = new TrainerRequest("in", "01034444444", 3, 15000);

        trainerRepository.save(Trainer.createTrainer(trainer1));
        trainerRepository.save(Trainer.createTrainer(trainer2));

        List<Trainer> all = trainerRepository.findAllByCareerGreaterThanEqual(4);

        Assertions.assertAll(
                () -> assertThat(all.size()).isEqualTo(1),
                () -> assertThat(all.get(0).getName()).isEqualTo("jun"),
                () -> assertThat(all.get(0).getHourlyPrice()).isEqualTo(10000)
        );

    }
}