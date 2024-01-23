package com.devgym.gymmanager.member.api;

import com.devgym.gymmanager.member.application.RefreshTokenService;
import com.devgym.gymmanager.member.dto.request.RefreshRequest;
import com.devgym.gymmanager.member.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService service;

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponse> reGetToken(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(service.reGetToken(request.memberName()));
    }
}
