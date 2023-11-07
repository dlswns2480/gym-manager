package com.devgym.gymmanager.TestData.member;

import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.dto.request.TrainerRequest;

public class TrainerData {
    public static TrainerRequest getTrainerRequest(){
        return new TrainerRequest("injunT", "0104434124234", 6, 15000);
    }
    public static Trainer getTrainer(){
        return Trainer.createTrainer(getTrainerRequest());
    }
}
