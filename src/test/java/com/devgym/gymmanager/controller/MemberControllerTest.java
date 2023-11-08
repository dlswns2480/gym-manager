package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.TrainerData;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Trainer;
import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.AddTrainer;
import com.devgym.gymmanager.dto.request.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends BaseIntegrationTest {
    @Test
    @DisplayName("회원 생성 api 호출에 성공한다")
    void createMember() throws Exception {
        MemberRequest memberRequest = MemberData.getMemberRequest();
        mvc.perform(post("/member/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(memberRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(memberRequest.name()))
                .andExpect(jsonPath("$.membership").value(memberRequest.membership().toString()));
    }

    @Test
    @DisplayName("회원을 멤버십을 통해 조회할 수 있다")
    void findByMembership() throws Exception {
        Member member = MemberData.getMember();
        memberRepository.save(member);
        mvc.perform(get("/member/membership")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(asJsonString(Membership.HALF_YEAR)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(member.getName()))
                .andExpect(jsonPath("$[0].membership").value(Membership.HALF_YEAR.toString()));
    }

    @Test
    @DisplayName("회원을 전체 조회할 수 있다")
    void findAll() throws Exception {
        mvc.perform(get("/member")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("hyunjung"))
                .andExpect(jsonPath("$[0].membership").value(Membership.HALF_YEAR.toString()));
    }

    @Test
    @DisplayName("회원에게 트레이너를 지정할 수 있다")
    void registerPt() throws Exception{
        Member member = MemberData.getMember();
        Trainer trainer = TrainerData.getTrainer();
        memberRepository.save(member);
        trainerRepository.save(trainer);
        AddTrainer addTrainer = new AddTrainer(member.getId(), trainer.getId());
        mvc.perform(post("/member/register-pt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(addTrainer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(trainer.getName()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer.getPhoneNumber()))
                .andExpect(jsonPath("$.career").value(trainer.getCareer()));
    }
}