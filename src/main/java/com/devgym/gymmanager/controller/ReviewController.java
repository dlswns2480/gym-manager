package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.ReviewRequest;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.service.ReviewService;
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
    @PostMapping("/create")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request){
        return new ResponseEntity<>(reviewService.createReview(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findAll() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findReviewGreaterThan(@RequestParam int score) {
        return new ResponseEntity<>(reviewService.findByScore(score), HttpStatus.OK);
    }
}
