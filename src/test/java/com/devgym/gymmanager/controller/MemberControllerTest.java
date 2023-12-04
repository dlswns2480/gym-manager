package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.TrainerData;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.dto.request.LoginRequest;
import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.trainer.domain.Trainer;
import com.devgym.gymmanager.trainer.dto.request.AddTrainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.devgym.gymmanager.member.domain.Membership.HALF_YEAR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends BaseIntegrationTest {
    @Autowired
    private BCryptPasswordEncoder encoder;
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
    @DisplayName("회원 생성 api 호출에 성공한다")
    void signUp() throws Exception {
        SignUpRequest memberRequest = MemberData.getMemberRequest();
        mvc.perform(post("/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(memberRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(memberRequest.memberName()))
                .andExpect(jsonPath("$.membership").value(memberRequest.membership().toString()));
    }
    @Test
    @DisplayName("로그인 후 토큰을 정상 응답 받는다.")
    void login() throws Exception{
        String encodedPassword = encoder.encode("tomato2480*");
        SignUpRequest signUpRequest = new SignUpRequest("injun@naver.com", encodedPassword, "010-3462-2480", HALF_YEAR);
        Member member = Member.createMember(signUpRequest);
        memberRepository.save(member);
        LoginRequest loginRequest = MemberData.getLoginRequest();
        mvc.perform(post("/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    @DisplayName("회원을 멤버십을 통해 조회할 수 있다")
    void findByMembership() throws Exception {
        Member member = MemberData.getMember();
        memberRepository.save(member);
        mvc.perform(get("/member/membership")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders)
                 .content(asJsonString(HALF_YEAR)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(member.getName()))
                .andExpect(jsonPath("$[0].membership").value(HALF_YEAR.toString()));
    }

    @Test
    @DisplayName("회원을 전체 조회할 수 있다")
    void findAll() throws Exception {
        Member member = MemberData.getMember();
        memberRepository.save(member);
        mvc.perform(get("/member")
                        .headers(httpHeaders)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value(member.getName()))
                .andExpect(jsonPath("$[0].membership").value(member.getMembership().toString()));
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
                        .headers(httpHeaders)
                .content(asJsonString(addTrainer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(trainer.getName()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer.getPhoneNumber()))
                .andExpect(jsonPath("$.career").value(trainer.getCareer()));
    }
}