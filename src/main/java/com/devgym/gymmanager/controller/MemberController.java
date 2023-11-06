package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.MemberRequest;
import com.devgym.gymmanager.dto.MemberResponse;
import com.devgym.gymmanager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @PostMapping("/create")
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request){
        return new ResponseEntity<>(service.createMember(request), HttpStatus.OK);
    }
}
