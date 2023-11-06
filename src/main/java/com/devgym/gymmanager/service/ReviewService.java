package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Review;
import com.devgym.gymmanager.dto.ReviewRequest;
import com.devgym.gymmanager.dto.ReviewResponse;
import com.devgym.gymmanager.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse createReview(ReviewRequest request){
        Review review = Review.createReview(request);
        Review result = reviewRepository.save(review);
        return new ReviewResponse(result.getScore(), result.getContent());
    }

}
