package com.devgym.gymmanager.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Trainer {
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
}
