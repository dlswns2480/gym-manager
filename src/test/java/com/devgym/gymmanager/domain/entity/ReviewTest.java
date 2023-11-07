package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.dto.request.ReviewRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    @Test
    @DisplayName("100점보다 높은 점수의 리뷰 생성 시 예외가 발생한다")
    void createReviewWithGreaterThan100(){
        ReviewRequest request = new ReviewRequest(1L, 101, "good");
        Assertions.assertThrows(IllegalStateException.class, () -> Review.createReview(request));
    }
}