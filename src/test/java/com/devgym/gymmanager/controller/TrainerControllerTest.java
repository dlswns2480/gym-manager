package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.TrainerData;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.trainer.dto.request.TrainerRequest;
import com.devgym.gymmanager.trainer.dto.response.TrainerResponse;
import com.devgym.gymmanager.trainer.application.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TrainerControllerTest extends BaseIntegrationTest {
    @Autowired
    TrainerService trainerService;
    @Value("${jwt.access-secret}")
    private String accessSecret;
    private final HttpHeaders httpHeaders = new HttpHeaders();
    @BeforeEach
    void setUp(){
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + JwtUtil.createAccessToken("injun@naver.com", accessSecret, 1000 * 60L));
        httpHeaders.add("Token-Type", "access");
    }
    @AfterEach
    void tearDown(){

    }
    @Test
    @DisplayName("트레이너 생성 api를 호출할 수 있다")
    void createTrainer() throws Exception {
        TrainerRequest trainerRequest = TrainerData.getTrainerRequest();
        mvc.perform(post("/trainer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders)
                        .content(asJsonString(trainerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(trainerRequest.name()))
                .andExpect(jsonPath("$.phoneNumber").value(trainerRequest.phoneNumber()));
    }

    @Test
    @DisplayName("특정 경력 이상의 트레이너를 전체 조회할 수 있다")
    void findAllByCarrer() throws Exception{
        TrainerRequest trainerRequest = TrainerData.getTrainerRequest();
        TrainerResponse trainer = trainerService.createTrainer(trainerRequest);
        mvc.perform(get("/trainer/{career}", trainer.career())
                        .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(trainer.name()))
                .andExpect(jsonPath("$[0].phoneNumber").value(trainer.phoneNumber()))
                .andExpect(jsonPath("$[0].career").value(trainer.career()));
    }
}