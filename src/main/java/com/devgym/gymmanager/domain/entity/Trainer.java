package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private List<Member> memberList;

    @Builder(access = AccessLevel.PRIVATE)
    public Trainer(TrainerRequest trainerRequest) {
        this.name = trainerRequest.name();
        this.phoneNumber = trainerRequest.phoneNumber();
        this.career = trainerRequest.career();
        this.hourlyPrice = trainerRequest.hourlyPrice();
    }
    public Trainer createTrainer(TrainerRequest trainerRequest){
        String phoneNumber = trainerRequest.phoneNumber();
        if(!phoneNumber.startsWith("010")){
            throw new IllegalStateException("올바르지 않은 전화번호 형식입니다");
        }
        return Trainer.builder().trainerRequest(trainerRequest).build();
    }

    public void addMember(Member member){
        if(this.memberList.contains(member)){
            throw new IllegalArgumentException("이미 교육중인 회원 중 하나입니다.");
        }
        memberList.add(member);
    }
}
