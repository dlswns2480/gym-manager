package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Review;
import com.devgym.gymmanager.dto.request.ReviewRequest;
import com.devgym.gymmanager.dto.response.ReviewResponse;
import com.devgym.gymmanager.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    /*
    * 리뷰 생성*/
    @Transactional
    public ReviewResponse createReview(ReviewRequest request){
        Member member = memberService.findByIdService(request.memberId());
        Review review = Review.createReview(request);
        review.setMember(member);
        Review result = reviewRepository.save(review);
        return new ReviewResponse(member.getName(), result.getScore(), result.getContent());
    }
    /*
    * 전체 조회*/
    public List<ReviewResponse> findAll(){
        List<Review> all = reviewRepository.findAll();
        return all.stream()
                .map(review ->
                new ReviewResponse(memberService.findByIdService(review.getMember().getId()).getName(),
                        review.getScore(),
                        review.getContent()))
                .toList();
    }
    /*
    * 별점으로 조회*/
    public List<ReviewResponse> findByScore(int score){
        List<Review> all = reviewRepository.findByScore(score);
        return all.stream()
                .map(review ->
                        new ReviewResponse(memberService.findByIdService(review.getMember().getId()).getName(),
                                review.getScore(),
                                review.getContent()))
                .toList();
    }

}
