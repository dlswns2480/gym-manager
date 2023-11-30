package com.devgym.gymmanager.member.api;

import com.devgym.gymmanager.member.dto.request.LoginRequest;
import com.devgym.gymmanager.member.dto.request.SignUpRequest;
import com.devgym.gymmanager.member.domain.Membership;
import com.devgym.gymmanager.common.validator.SignUpValidator;
import com.devgym.gymmanager.trainer.dto.request.AddTrainer;
import com.devgym.gymmanager.member.dto.response.MemberResponse;
import com.devgym.gymmanager.trainer.dto.response.TrainerResponse;
import com.devgym.gymmanager.member.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final SignUpValidator signUpValidator;
    private final MemberService service;
    @InitBinder("signUpRequest")
    public void signupInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpValidator);
    }
    @Operation(summary = "회원가입 요청")
    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signUp(@Valid @RequestBody SignUpRequest request){
        return ResponseEntity.ok(service.signUp(request));
    }
    @Operation(summary = "로그인 요청")
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginRequest request){
        return ResponseEntity.ok(service.signIn(request));
    }
    @Operation(summary = "회원 조회 요청", description = "멤버십을 통해 회원 정보를 조회합니다.", tags = { "MemberController" })
    @GetMapping("/membership")
    public ResponseEntity<List<MemberResponse>> findByMemberShip(@RequestBody Membership membership) {
        return new ResponseEntity<>(service.findByMembership(membership), HttpStatus.OK);
    }
    @Operation(summary = "회원 조회 요청", description = "전체 회원정보를 조회합니다.", tags = { "MemberController" })
    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return new ResponseEntity<>(service.findAllMembers(), HttpStatus.OK);
    }
    @Operation(summary = "트레이너 등록 요청", description = "회원에게 트레이너가 지정됩니다.", tags = { "MemberController" })
    @PostMapping("/register-pt")
    public ResponseEntity<TrainerResponse> registerTrainer(@RequestBody AddTrainer request) {
        return new ResponseEntity<>(service.registerTrainer(request), HttpStatus.OK);
    }
}
