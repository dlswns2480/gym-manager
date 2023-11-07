package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.exception.NotFoundInfoException;
import com.devgym.gymmanager.repository.TrainerRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    private void validateDuplicateTrainer(String phoneNumber) {
        Optional<Trainer> trainer = trainerRepository.findByPhoneNumber(phoneNumber);
        if(trainer.isPresent()){
            throw new DuplicateRequestException("이미 존재하는 트레이너입니다");
        }
    }
    public TrainerResponse createTrainer(TrainerRequest request){
        validateDuplicateTrainer(request.phoneNumber());
        Trainer trainer = Trainer.createTrainer(request);
        Trainer save = trainerRepository.save(trainer);
        return new TrainerResponse(save.getName(), save.getPhoneNumber(), save.getCareer(), save.getHourlyPrice());
    }

    public List<TrainerResponse> findByCareerGreaterThan(int career) {
        if(career < 0){
            throw new IllegalArgumentException("0 이상의 숫자를 입력해주세요");
        }
        List<Trainer> all = trainerRepository.findAllByCareerGreaterThanEqual(career);
        return all.stream()
                .map(t -> new TrainerResponse(t.getName(), t.getPhoneNumber(), t.getCareer(), t.getHourlyPrice()))
                .toList();
    }

    protected Trainer findByIdService(Long trainerId) {
        return trainerRepository.findById(trainerId).orElseThrow(NotFoundInfoException::new);
    }

}
