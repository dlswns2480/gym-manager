package com.devgym.gymmanager.member.domain;

import com.devgym.gymmanager.common.BaseEntity;
import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.review.domain.Review;
import com.devgym.gymmanager.trainer.domain.Trainer;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String passWord;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Membership membership;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Trainer trainer;

    @OneToMany(mappedBy = "member", orphanRemoval = true) // lazy가 디폴트이다!!
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private Member(String name, String passWord, String phoneNumber, Membership membership) {
        this.name = name;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.membership = membership;
    }

    public static Member createMember(SignUpRequest memberRequest) {
        String name = memberRequest.memberName();
        String passWord = memberRequest.passWord();
        String phoneNumber = memberRequest.phoneNumber();
        Membership membership = memberRequest.membership();
        if (!phoneNumber.startsWith("010")) {
            throw new IllegalStateException("올바르지 않은 전화번호 형식입니다.");
        }
        return new Member(name, passWord, phoneNumber, membership);
    }

    public void setTrainer(Trainer trainer) {
        if (this.trainer != null) {
            this.trainer.getMemberList().remove(this);
        }
        this.trainer = trainer;
        trainer.getMemberList().add(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Member member = (Member) object;
        return Objects.equals(id, member.id) && Objects.equals(name, member.name) && Objects.equals(
            phoneNumber, member.phoneNumber) && membership == member.membership;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, membership);
    }
}
