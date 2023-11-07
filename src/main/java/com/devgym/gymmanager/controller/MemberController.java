package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.MemberRequest;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.service.MemberService;
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

    @PostMapping("/create")
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request){
        return new ResponseEntity<>(service.createMember(request), HttpStatus.OK);
    }
    @GetMapping("/membership")
    public ResponseEntity<MemberResponse> findByMemberShip(@RequestBody Membership membership){
        return new ResponseEntity<>(service.findByMembership(membership), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll(){
        return new ResponseEntity<>(service.findAllMembers(), HttpStatus.OK);
    }
}
