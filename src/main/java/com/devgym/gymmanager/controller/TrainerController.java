package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.TrainerRequest;
import com.devgym.gymmanager.dto.response.ErrorResponse;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.TrainerService;
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
@Tag(name = "Trainer", description = "트레이너 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;
    @Operation(summary = "트레이너 생성 요청", description = "트레이너 정보가 생성됩니다.", tags = { "TrainerController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "트레이너 정보 생성 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TrainerResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/create")
    public ResponseEntity<TrainerResponse> createTrainer(@RequestBody TrainerRequest request) {
        return new ResponseEntity<>(trainerService.createTrainer(request), HttpStatus.OK);
    }
    @Operation(summary = "트레이너 조회 요청", description = "특정 경력 이상의 트레이너들을 조회합니다.", tags = { "TrainerController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "트레이너 정보 조회 성공",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TrainerResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("/{career}")
    public ResponseEntity<List<TrainerResponse>> findByCareer(@PathVariable int career){
        return new ResponseEntity<>(trainerService.findByCareerGreaterThan(career), HttpStatus.OK);
    }
}
