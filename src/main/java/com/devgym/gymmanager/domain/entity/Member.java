package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.MemberRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String phoneNumber;
    private Membership membership;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Trainer trainer;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    private Member(String name, String phoneNumber, Membership membership) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.membership = membership;
    }

    public static Member createMember(MemberRequest memberRequest){
        String name = memberRequest.name();
        String phoneNumber = memberRequest.phoneNumber();
        Membership membership = memberRequest.membership();
        if(!phoneNumber.startsWith("010")){
            throw new IllegalStateException("올바르지 않은 전화번호 형식입니다.");
        }
        return new Member(name, phoneNumber, membership);
    }

    public void setTrainer(Trainer trainer){
        if (this.trainer != null) {
            this.trainer.getMemberList().remove(this);
        }
        this.trainer = trainer;
        trainer.getMemberList().add(this);
    }

}
