package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.dto.TrainerRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Trainer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;
    private String name;
    private String phoneNumber;
    private int career;
    private int hourlyPrice;
    @OneToMany(mappedBy = "trainer")
    private List<Member> memberList;

    @Builder
    public Trainer(TrainerRequest trainerRequest) {
        this.name = trainerRequest.name();
        this.phoneNumber = trainerRequest.phoneNumber();
        this.career = trainerRequest.career();
        this.hourlyPrice = trainerRequest.hourlyPrice();
    }

    public void addMember(Member member){
        memberList.add(member);
    }
}
