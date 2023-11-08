package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.AddTrainer;
import com.devgym.gymmanager.dto.request.MemberRequest;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;
    @ApiOperation(value = "회원 생성", notes = "회원을 생성한다")
    @PostMapping("/create")
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request) {
        return new ResponseEntity<>(service.createMember(request), HttpStatus.OK);
    }
    @ApiOperation(value = "회원 조회", notes = "멤버쉽을 통해 회원을 조회한다")
    @GetMapping("/membership")
    public ResponseEntity<MemberResponse> findByMemberShip(@RequestBody Membership membership) {
        return new ResponseEntity<>(service.findByMembership(membership), HttpStatus.OK);
    }
    @ApiOperation(value = "회원 조회", notes = "등록된 회원을 전체 조회한다")
    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return new ResponseEntity<>(service.findAllMembers(), HttpStatus.OK);
    }
    @ApiOperation(value = "트레이너 등록", notes = "회원에게 트레이너를 지정해준다")
    @PostMapping("/register-pt")
    public ResponseEntity<Long> registerTrainer(@RequestBody AddTrainer request) {
        return new ResponseEntity<>(service.registerTrainer(request), HttpStatus.OK);
    }
}
