package com.devgym.gymmanager.review.domain;

import com.devgym.gymmanager.common.BaseEntity;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.review.dto.request.ApiReviewRequest;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Builder(access = AccessLevel.PRIVATE)
    private Review(ApiReviewRequest reviewRequest) {
        setMember(reviewRequest.member());
        this.score = reviewRequest.score();
        this.content = reviewRequest.content();
    }

    public static Review createReview(ApiReviewRequest reviewRequest) { //아예 생성자로도 고려
        int score = reviewRequest.score();
        if (score > 100) {
            throw new IllegalStateException("100점 미만의 점수만 입력 가능합니다");
        }

        return Review.builder().reviewRequest(reviewRequest).build();
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        member.getReviews().add(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Review review = (Review) object;
        return score == review.score && Objects.equals(id, review.id) && Objects.equals(content,
            review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, content);
    }
}
