package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.common.TestContainerSupport;
import com.devgym.gymmanager.member.repository.MemberRepository;
import com.devgym.gymmanager.trainer.repository.TrainerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class BaseIntegrationTest extends TestContainerSupport{
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected TrainerRepository trainerRepository;
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
