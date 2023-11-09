package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
public class Trainer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    private String name;
    private String phoneNumber;
    private int career;
    private int hourlyPrice;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Member> memberList = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Trainer(TrainerRequest trainerRequest) {
        this.name = trainerRequest.name();
        this.phoneNumber = trainerRequest.phoneNumber();
        this.career = trainerRequest.career();
        this.hourlyPrice = trainerRequest.hourlyPrice();
    }
    public static Trainer createTrainer(TrainerRequest trainerRequest){
        String phoneNumber = trainerRequest.phoneNumber();
        if(!phoneNumber.startsWith("010")){
            throw new IllegalStateException("올바르지 않은 전화번호 형식입니다");
        }
        return Trainer.builder().trainerRequest(trainerRequest).build();
    }

}
