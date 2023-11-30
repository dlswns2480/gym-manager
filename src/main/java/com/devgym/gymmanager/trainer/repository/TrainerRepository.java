package com.devgym.gymmanager.trainer.repository;

import com.devgym.gymmanager.trainer.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query("select t from Trainer t where t.career >= :career")
    List<Trainer> findAllByCareerGreaterThanEqual(int career);

    Optional<Trainer> findByPhoneNumber(String phoneNumber);
}
