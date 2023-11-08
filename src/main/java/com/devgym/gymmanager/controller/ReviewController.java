package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.ReviewRequest;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @ApiOperation(value = "리뷰 생성", notes = "리뷰를 생성한다")
    @PostMapping("/create")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request){
        return new ResponseEntity<>(reviewService.createReview(request), HttpStatus.OK);
    }
    @ApiOperation(value = "리뷰 조회", notes = "등록된 리뷰를 전체 조회한다")
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findAll() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "리뷰 조회", notes = "특정 점수 이상인 리뷰들을 조회한다")
    @GetMapping("/{score}")
    public ResponseEntity<List<ReviewResponse>> findReviewGreaterThan(@PathVariable int score) {
        return new ResponseEntity<>(reviewService.findByScore(score), HttpStatus.OK);
    }
}
