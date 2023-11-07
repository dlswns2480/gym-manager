package com.devgym.gymmanager.TestData.member;

import com.devgym.gymmanager.domain.entity.Review;
import com.devgym.gymmanager.dto.request.ReviewRequest;

public class ReviewData {
    public static ReviewRequest getReviewRequest(){
        return new ReviewRequest(1L, 4, "4score content");
    }
    public static ReviewRequest getReviewRequest2(){
        return new ReviewRequest(1L, 3, "3score content");
    }
    public static Review getReview(){
        return Review.createReview(getReviewRequest());
    }
    public static Review getReview2(){
        return Review.createReview(getReviewRequest());
    }
}
