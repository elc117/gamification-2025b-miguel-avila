package com.mubification.services;

import com.mubification.models.Review;
import com.mubification.models.ReviewDTO;
import com.mubification.repositories.ReviewRepository;

import java.util.List;

public class ReviewService {

    private ReviewRepository reviewRepository;
    private AchievementService achievementService = new AchievementService();
    
    public ReviewService() {
        this.reviewRepository = new ReviewRepository();
    }

    public Review addReview(Review review) {
        Review r = reviewRepository.addReview(review);
        //achievementService.checkAchievements(review.getUserId());
        return r;
    }

    public List<ReviewDTO> getTopReviews(int limit) {
        return reviewRepository.getTopReviews(limit);
    }

    public List<ReviewDTO> getUserTopReviews(int limit, int user) {
        return reviewRepository.getUserTopReviews(limit, user);
    }

}
