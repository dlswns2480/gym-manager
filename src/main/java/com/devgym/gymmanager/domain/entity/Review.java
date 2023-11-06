package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.dto.ReviewRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private int score;
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @Builder(access = AccessLevel.PRIVATE)
    private Review(ReviewRequest reviewRequest){
        this.member = reviewRequest.member();
        this.score = reviewRequest.score();
        this.content = reviewRequest.content();
    }
    public static Review createReview(ReviewRequest reviewRequest){
        int score = reviewRequest.score();
        String content = reviewRequest.content();
        if(score > 100){
            throw new IllegalStateException("100점 미만의 점수만 입력 가능합니다");
        }
        return Review.builder().reviewRequest(reviewRequest).build();
    }
}
