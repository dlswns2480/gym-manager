package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.TrainerService;
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

    @PostMapping("/create")
    public ResponseEntity<TrainerResponse> createTrainer(@RequestBody TrainerRequest request) {
        return new ResponseEntity<>(trainerService.createTrainer(request), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TrainerResponse>> findByCareer(@RequestParam int career){
        return new ResponseEntity<>(trainerService.findByCareerGreaterThan(career), HttpStatus.OK);
    }
}
