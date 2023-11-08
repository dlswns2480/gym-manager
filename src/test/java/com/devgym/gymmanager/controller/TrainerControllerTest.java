package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.TrainerData;
import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.TrainerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TrainerControllerTest extends BaseIntegrationTest {
    @Autowired
    TrainerService trainerService;
    @Test
    @DisplayName("트레이너 생성 api를 호출할 수 있다")
    void createTrainer() throws Exception {
        TrainerRequest trainerRequest = TrainerData.getTrainerRequest();
        mvc.perform(post("/trainer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(trainerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(trainerRequest.name()))
                .andExpect(jsonPath("$.phoneNumber").value(trainerRequest.phoneNumber()));
    }

    @Test
    @DisplayName("특정 경력 이상의 트레이너를 전체 조회할 수 있다")
    void findAllByCarrer() throws Exception{
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        TrainerRequest trainerRequest = TrainerData.getTrainerRequest();
        TrainerResponse trainer = trainerService.createTrainer(trainerRequest);
        map.add("career", String.valueOf(trainer.career()));
        mvc.perform(get("/trainer/{career}", trainer.career())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(trainer.name()))
                .andExpect(jsonPath("$[0].phoneNumber").value(trainer.phoneNumber()))
                .andExpect(jsonPath("$[0].career").value(trainer.career()));
    }
}