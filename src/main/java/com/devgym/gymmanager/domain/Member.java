package com.devgym.gymmanager.domain;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String phoneNumber;
    private Membership membership;
    private Boolean useLocker;
    private String email;
    @ManyToOne
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Trainer trainer;

}
