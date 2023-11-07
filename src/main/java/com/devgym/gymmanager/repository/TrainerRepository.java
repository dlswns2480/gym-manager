package com.devgym.gymmanager.repository;

import com.devgym.gymmanager.domain.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByCareerGreaterThanEqual(int career);
}
