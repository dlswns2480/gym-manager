package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.TrainerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;
    @ApiOperation(value = "트레이너 생성", notes = "트레이너를 생성한다")
    @PostMapping("/create")
    public ResponseEntity<TrainerResponse> createTrainer(@RequestBody TrainerRequest request) {
        return new ResponseEntity<>(trainerService.createTrainer(request), HttpStatus.OK);
    }
    @ApiOperation(value = "트레이너 조회", notes = "등록된 트레이너를 전체 조회한댜")
    @GetMapping
    public ResponseEntity<List<TrainerResponse>> findByCareer(@RequestParam int career){
        return new ResponseEntity<>(trainerService.findByCareerGreaterThan(career), HttpStatus.OK);
    }
}
