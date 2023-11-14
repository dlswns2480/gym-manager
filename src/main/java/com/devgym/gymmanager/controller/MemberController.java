package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.domain.type.Membership;
import com.devgym.gymmanager.dto.request.AddTrainer;
import com.devgym.gymmanager.dto.request.MemberRequest;
import com.devgym.gymmanager.dto.response.ErrorResponse;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;
    @Operation(summary = "회원 생성 요청", description = "회원 정보가 생성됩니다.", tags = { "MemberController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 생성 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))), //usereturntype

    })
    @PostMapping("/create")
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request) {
        return new ResponseEntity<>(service.createMember(request), HttpStatus.OK);
    }
    @Operation(summary = "회원 조회 요청", description = "멤버십을 통해 회원 정보를 조회합니다.", tags = { "MemberController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 조회 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("/membership")
    public ResponseEntity<List<MemberResponse>> findByMemberShip(@RequestBody Membership membership) {
        return new ResponseEntity<>(service.findByMembership(membership), HttpStatus.OK);
    }
    @Operation(summary = "회원 조회 요청", description = "전체 회원정보를 조회합니다.", tags = { "MemberController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 조회 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return new ResponseEntity<>(service.findAllMembers(), HttpStatus.OK);
    }
    @Operation(summary = "트레이너 등록 요청", description = "회원에게 트레이너가 지정됩니다.", tags = { "MemberController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "트레이너 등록 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TrainerResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/register-pt")
    public ResponseEntity<TrainerResponse> registerTrainer(@RequestBody AddTrainer request) {
        return new ResponseEntity<>(service.registerTrainer(request), HttpStatus.OK);
    }
}
