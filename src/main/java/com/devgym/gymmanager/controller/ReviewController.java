package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.ReviewRequest;
import com.devgym.gymmanager.dto.response.ErrorResponse;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.service.ReviewService;
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
@Tag(name = "Review", description = "리뷰 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @Operation(summary = "리뷰 생성 요청", description = "리뷰 정보가 생성됩니다.", tags = { "ReviewController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 정보 생성 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/create")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request){
        return new ResponseEntity<>(reviewService.createReview(request), HttpStatus.OK);
    }
    @Operation(summary = "리뷰 조회 요청", description = "전체 리뷰 정보를 조회합니다.", tags = { "ReviewController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 리뷰 조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findAll() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }
    @Operation(summary = "리뷰 조회 요청", description = "특정 점수 이상의 리뷰들을 조회합니다.", tags = { "ReviewController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("/{score}")
    public ResponseEntity<List<ReviewResponse>> findReviewGreaterThan(@PathVariable int score) {
        return new ResponseEntity<>(reviewService.findByScore(score), HttpStatus.OK);
    }
}
