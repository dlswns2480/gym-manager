package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.ReviewData;
import com.devgym.gymmanager.member.application.MemberService;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.review.domain.Review;
import com.devgym.gymmanager.review.dto.request.ApiReviewRequest;
import com.devgym.gymmanager.review.dto.request.ReviewRequest;
import com.devgym.gymmanager.review.dto.response.ReviewResponse;
import com.devgym.gymmanager.review.application.ReviewService;
import com.devgym.gymmanager.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @Mock
    MemberService memberService;
    @Mock
    ReviewRepository reviewRepository;
    @InjectMocks
    ReviewService reviewService;

    @Test
    @DisplayName("리뷰를 생성할 수 있다")
    void create() {
        ApiReviewRequest ApireviewRequest = ReviewData.getReviewRequest2();
        ReviewRequest reviewRequest = new ReviewRequest(1L, ApireviewRequest.score(), ApireviewRequest.content());
        Member member = MemberData.getMember();

        when(memberService.findByIdService(any(Long.class))).thenReturn(member);
        when(reviewRepository.save(any(Review.class)))
                .thenReturn(Review.createReview(ApireviewRequest));

        ReviewResponse review = reviewService.createReview(reviewRequest);

        assertAll(
                () -> assertThat(review.score()).isEqualTo(reviewRequest.score()),
                () -> assertThat(review.content()).isEqualTo(reviewRequest.content()),
                () -> assertThat(review.memberName()).isEqualTo(member.getName())
        );
    }

}