package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Trainer", description = "트레이너 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
@Slf4j
public class TrainerController {
    private final TrainerService trainerService;
    @Operation(summary = "트레이너 생성 요청", description = "트레이너 정보가 생성됩니다.", tags = { "TrainerController" })
    @PostMapping("/create")
    public ResponseEntity<TrainerResponse> createTrainer(Authentication authentication, @RequestBody TrainerRequest request) {
        log.info("{}님이 등록한 트레이너입니다.", authentication.getName());
        return new ResponseEntity<>(trainerService.createTrainer(request), HttpStatus.OK);
    }
    @Operation(summary = "트레이너 조회 요청", description = "특정 경력 이상의 트레이너들을 조회합니다.", tags = { "TrainerController" })
    @GetMapping("/{career}")
    public ResponseEntity<List<TrainerResponse>> findByCareer(@PathVariable int career){
        return new ResponseEntity<>(trainerService.findByCareerGreaterThan(career), HttpStatus.OK);
    }
}
