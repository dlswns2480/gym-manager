package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.ReviewData;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Review;
import com.devgym.gymmanager.dto.request.ReviewRequest;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.repository.ReviewRepository;
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
        ReviewRequest reviewRequest = ReviewData.getReviewRequest();
        Member member = MemberData.getMember();

        when(memberService.findByIdService(any(Long.class))).thenReturn(member);
        when(reviewRepository.save(any(Review.class)))
                .thenReturn(Review.createReview(reviewRequest));

        ReviewResponse review = reviewService.createReview(reviewRequest);

        assertAll(
                () -> assertThat(review.score()).isEqualTo(reviewRequest.score()),
                () -> assertThat(review.content()).isEqualTo(reviewRequest.content()),
                () -> assertThat(review.memberName()).isEqualTo(member.getName())
        );
    }

}