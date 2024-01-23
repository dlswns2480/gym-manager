package com.devgym.gymmanager.review.application;

import com.devgym.gymmanager.member.application.MemberService;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.review.domain.Review;
import com.devgym.gymmanager.review.dto.request.ApiReviewRequest;
import com.devgym.gymmanager.review.dto.request.ReviewRequest;
import com.devgym.gymmanager.review.dto.response.ReviewResponse;
import com.devgym.gymmanager.review.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberService memberService;

    /*
     * 리뷰 생성*/
    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        Member member = memberService.findByIdService(request.memberId()); // 회원 확인
        ApiReviewRequest apiReviewRequest = new ApiReviewRequest(member, request.score(),
            request.content());
        Review review = Review.createReview(apiReviewRequest); // 리뷰 생성
        Review result = reviewRepository.save(review);
        return new ReviewResponse(member.getName(), result.getScore(), result.getContent());
    }

    /*
     * 전체 조회*/
    public List<ReviewResponse> findAll() {
        List<Review> all = reviewRepository.findAll();
        return all.stream()
            .map(review ->
                new ReviewResponse(
                    memberService.findByIdService(review.getMember().getId()).getName(),
                    review.getScore(),
                    review.getContent()))
            .toList();
    }

    /*
     * 별점으로 조회*/
    public List<ReviewResponse> findByScore(int score) {
        List<Review> all = reviewRepository.findByScore(score);
        return all.stream()
            .map(review ->
                new ReviewResponse(
                    memberService.findByIdService(review.getMember().getId()).getName(),
                    review.getScore(),
                    review.getContent()))
            .toList();
    }

}
