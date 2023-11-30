package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.review.domain.Review;
import com.devgym.gymmanager.review.dto.request.ApiReviewRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReviewTest {
    @Test
    @DisplayName("100점보다 높은 점수의 리뷰 생성 시 예외가 발생한다")
    void createReviewWithGreaterThan100(){
        ApiReviewRequest reviewRequest = new ApiReviewRequest(MemberData.getMember(), 101, "good");
        Assertions.assertThrows(IllegalStateException.class, () -> Review.createReview(reviewRequest));
    }
}