package com.devgym.gymmanager.trainer.application;

import static com.devgym.gymmanager.common.exception.ErrorCode.NOT_EXIST_TRAINER;

import com.devgym.gymmanager.common.exception.CustomException;
import com.devgym.gymmanager.trainer.domain.Trainer;
import com.devgym.gymmanager.trainer.dto.request.TrainerRequest;
import com.devgym.gymmanager.trainer.dto.response.TrainerResponse;
import com.devgym.gymmanager.trainer.repository.TrainerRepository;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    private void validateDuplicateTrainer(String phoneNumber) {
        Optional<Trainer> trainer = trainerRepository.findByPhoneNumber(phoneNumber);
        if (trainer.isPresent()) {
            throw new DuplicateRequestException("이미 존재하는 트레이너입니다");
        }
    }

    @Transactional
    public TrainerResponse createTrainer(TrainerRequest request) {
        validateDuplicateTrainer(request.phoneNumber());
        Trainer trainer = Trainer.createTrainer(request);
        Trainer save = trainerRepository.save(trainer);
        return new TrainerResponse(save.getName(), save.getPhoneNumber(), save.getCareer(),
            save.getHourlyPrice());
    }

    public List<TrainerResponse> findByCareerGreaterThan(int career) {
        if (career < 0) {
            throw new IllegalArgumentException("0 이상의 숫자를 입력해주세요");
        }
        List<Trainer> all = trainerRepository.findAllByCareerGreaterThanEqual(career);
        return all.stream()
            .map(t -> new TrainerResponse(t.getName(), t.getPhoneNumber(), t.getCareer(),
                t.getHourlyPrice()))
            .toList();
    }

    public Trainer findByIdService(Long trainerId) {
        return trainerRepository.findById(trainerId)
            .orElseThrow(() -> new CustomException(NOT_EXIST_TRAINER));
    }

}
