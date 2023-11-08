package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;
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
                .andExpect(jsonPath("$[0].name").value("hyunjung"))
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
}