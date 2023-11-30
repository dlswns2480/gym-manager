package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.review.domain.Review;
import com.devgym.gymmanager.review.dto.request.ApiReviewRequest;

public class ReviewData {
    public static ApiReviewRequest getReviewRequest(){
        return new ApiReviewRequest(MemberData.getMember(), 4, "4score content");
    }
    public static ApiReviewRequest getReviewRequest2(){
        return new ApiReviewRequest(MemberData.getMember2(), 3, "3score content");
    }
    public static Review getReview(){
        return Review.createReview(getReviewRequest());
    }
    public static Review getReview2(){
        return Review.createReview(getReviewRequest());
    }
}
