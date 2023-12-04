package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.review.dto.request.ReviewRequest;
import com.devgym.gymmanager.review.dto.response.ReviewResponse;
import com.devgym.gymmanager.review.application.ReviewService;
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

class ReviewControllerTest extends BaseIntegrationTest {
    @Autowired
    ReviewService reviewService;
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
    @DisplayName("리뷰를 생성할 수 있다")
    void create() throws Exception {
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        ReviewRequest reviewRequest = new ReviewRequest(save.getId(), 20, "good");
        mvc.perform(post("/review/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders)
                        .content(asJsonString(reviewRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(reviewRequest.score()))
                .andExpect(jsonPath("$.content").value(reviewRequest.content()));
    }

    @Test
    @DisplayName("전체 리뷰를 조회할 수 있다")
    void findAll() throws Exception {
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        ReviewRequest request = new ReviewRequest(save.getId(), 20, "good");
        ReviewResponse review = reviewService.createReview(request);
        mvc.perform(get("/review")
                        .headers(httpHeaders)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].score").value(review.score()))
                .andExpect(jsonPath("$[0].content").value(review.content()));
    }

    @Test
    @DisplayName("특정 점수를 통해 리뷰를 조회할 수 있다")
    void findByScore() throws Exception{
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        ReviewRequest request = new ReviewRequest(save.getId(), 20, "good");
        ReviewResponse review = reviewService.createReview(request);
        mvc.perform(get("/review/{score}", 20)
                        .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].score").value(20))
                .andExpect(jsonPath("$[0].content").value(review.content()));
    }
}